package com.example.numanvaccine.presentation.favourites

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
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
import com.example.numanvaccine.presentation.utils.HelperUtil
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class FavouritesFragmentTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    @SmallTest
    fun test_onCountriesClickedCheckRecyclerViewIsDisplayed() {

        //click on the favourites fragment
        onView(withId(R.id.favouritesFragment)).perform(click())

        //Check recyclerview is displayed
        onView(withId(R.id.countriesList)).check(matches(isDisplayed()))
    }

    /*
    Precondition: Requires the first item in the list to be in the favourite state before the
    test is conducted
     */
    @Test
    @SmallTest
    fun test_onCountriesDisplayedFirstItemSetFavouriteItemToUnfavouriteItem() {

        //click on the favourites fragment
        onView(withId(R.id.favouritesFragment)).perform(click())

        //Click first favourite icon on first item of recyclerview
        onView(withId(R.id.countriesList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ClickOnImageView()

            )
        )

        //check icon matches
        HelperUtil.checkBackground(0, R.id.favouriteImageView, 0)
    }

    @Test
    @SmallTest
    fun test_onMoreDetailsClickedCheckDetailedViewIsDisplayed() {

        //click on the favourites fragment
        onView(withId(R.id.favouritesFragment)).perform(click())

        //click on the More Details, for the First Item
        onView(withId(R.id.countriesList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ClickOnMoreDetails()

            )
        )

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.detailed_fragment_parent_container))
            .check(matches(isDisplayed()))
    }
}