package com.example.numanvaccine.presentation.countries

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.numanvaccine.R
import com.example.numanvaccine.presentation.common.ClickOnImageView
import com.example.numanvaccine.presentation.common.ClickOnMoreDetails
import com.example.numanvaccine.presentation.common.MainActivity
import com.example.numanvaccine.presentation.utils.HelperUtil.checkBackground
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class CountriesFragmentTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    //click on the countries fragment
    @Before
    fun prerequisiteLoadDetailedPage() {

        //click on the countries fragment
        onView(withId(R.id.countriesFragment)).perform(click())
    }

    @Test
    @SmallTest
    fun test_onCountriesClickedCheckRecyclerViewIsDisplayed() {

        onView(withId(R.id.countriesList)).check(matches(isDisplayed()))
    }

    //Click on specific item , imageView or TextView
    fun clickOnSpecificItem(fn: () -> ViewAction) {
        onView(withId(R.id.countriesList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                fn()
            )
        )
    }

    /*
    Precondition: Requires the first item in the list to be in the unfavourite state before the
    test is conducted
     */
    @Test
    @SmallTest
    fun test_onCountriesDisplayedFirstItemSetUnfavouriteItemToFavouriteItem() {

        //click on the Favourites ImageView, for the First Item in List
        clickOnSpecificItem { ClickOnImageView() }

        checkBackground(0, R.id.favouriteImageView, 1)
    }

    /*
    Precondition: Requires the first item in the list to be in the favourite state before the
    test is conducted
     */
    @Test
    @SmallTest
    fun test_onCountriesDisplayedFirstItemSetFavouriteItemToUnfavouriteItem() {

        //click on the Favourites ImageView, for the First Item in List
        clickOnSpecificItem { ClickOnImageView() }

        //check icon matches
        checkBackground(0, R.id.favouriteImageView, 0)
    }

    @Test
    @SmallTest
    fun test_onMoreDetailsClickedCheckDetailedViewIsDisplayed() {

        //click on the More Details, for the First Item in List
        clickOnSpecificItem { ClickOnMoreDetails() }

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.detailed_fragment_parent_container))
            .check(matches(isDisplayed()))
    }

}