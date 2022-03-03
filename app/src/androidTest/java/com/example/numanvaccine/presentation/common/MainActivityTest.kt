package com.example.numanvaccine.presentation.common

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.numanvaccine.R
import com.example.numanvaccine.presentation.utils.EspressoUtils.hasCheckedItem
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    @SmallTest
    fun test_isActiviyInView() {
        onView(withId(R.id.container)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun test_isBottomNavigationViewHomeChecked() {
        onView(withId(R.id.bottomNavigationView)).check(matches(hasCheckedItem(R.id.titleFragment)))
    }

    @Test
    @SmallTest
    fun test_isBottomNavigationViewCountriesUnchecked() {
        onView(withId(R.id.bottomNavigationView)).check(matches(not(hasCheckedItem(R.id.countriesFragment))))
    }

    @Test
    @SmallTest
    fun test_isBottomNavigationViewFavouritiesUnchecked() {
        onView(withId(R.id.bottomNavigationView)).check(matches(not(hasCheckedItem(R.id.favouritesFragment))))
    }

    @Test
    @SmallTest
    fun test_onCountriesClickedCheckCountriesFragmentIsDisplayed() {
        onView(withId(R.id.countriesFragment)).perform(click())
        onView(withId(R.id.fragment_countries_parent)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun test_onFavouritesClickedCheckFavouritesFragmentIsDisplayed() {
        onView(withId(R.id.countriesFragment)).perform(click())
        onView(withId(R.id.fragment_countries_parent)).check(matches(isDisplayed()))
    }
}