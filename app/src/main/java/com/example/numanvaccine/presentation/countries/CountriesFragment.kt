package com.example.numanvaccine.presentation.ui.recipecollection

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numanvaccine.R
import com.example.numanvaccine.databinding.FragmentCountriesListBinding
import com.example.numanvaccine.domain.model.Country
import com.example.numanvaccine.presentation.ui.common.CountriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CountriesFragment : Fragment(R.layout.fragment_countries_list),
    CountriesAdapter.OnCountryItemClickListner {

    private lateinit var countriesAdapter: CountriesAdapter
    private val viewModel: CountriesViewModel by viewModels()

    private var _binding: FragmentCountriesListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCountriesListBinding.bind(view)

        setUpRecyclerView()

        binding.swipeToRefresh.apply {
            setOnRefreshListener {
                isRefreshing = false
                viewModel.refresh()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when (viewState) {
                        is CountriesViewState.Success -> {
                            binding.loadingCircularProgressIndicator.visibility = View.GONE
                            countriesAdapter.submitList(viewState.recipe)
                        }
                        CountriesViewState.Error -> {
                            binding.loadingCircularProgressIndicator.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                R.string.generic_error_message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        CountriesViewState.Loading -> {
                            binding.loadingCircularProgressIndicator.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.countriesList.apply {
            countriesAdapter = CountriesAdapter(this@CountriesFragment)
            adapter = countriesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onFavouriteClick(item: Country, position: Int, add: Int) {
        viewModel.Update(item)
    }

    override fun onItemClicked(item: Country, position: Int, add: Int) {
        val bundle = Bundle()
        bundle.putSerializable("country", item)
        findNavController().navigate(R.id.action_countriesFragment_to_detailedFragment, bundle)
    }
}