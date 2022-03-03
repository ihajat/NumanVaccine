package com.example.numanvaccine.presentation.detailed

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.numanvaccine.R
import com.example.numanvaccine.presentation.common.ClickOnMoreDetails
import com.example.numanvaccine.presentation.common.MainActivity
import com.example.numanvaccine.presentation.data.DetailedCountryTestData.getAdministered
import com.example.numanvaccine.presentation.data.DetailedCountryTestData.getArea
import com.example.numanvaccine.presentation.data.DetailedCountryTestData.getCountry
import com.example.numanvaccine.presentation.data.DetailedCountryTestData.getPeopleVaccinated
import com.example.numanvaccine.presentation.data.DetailedCountryTestData.getPopulation
import com.example.numanvaccine.presentation.data.DetailedCountryTestData.getRegion
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class DetailedFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()

        onView(withId(R.id.countriesFragment)).perform(click())

        //click on the More Details, for the First Item
        onView(withId(R.id.countriesList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ClickOnMoreDetails()

            )
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    @SmallTest
    fun test_isCorrectDetailedViewDisplayed() {

        // Confirm nav to DetailFragment
        onView(withId(R.id.detailed_fragment_parent_container))
            .check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun test_isCountryDisplayed() {

        //Check Country is displayed
        onView(withId(R.id.countryHeading)).check(matches(isDisplayed()))
            .check(matches(withText(R.string.label_country)))

        onView(withId(R.id.countryView)).check(matches(isDisplayed()))
            .check(matches(withText(getCountry())))
    }

    @Test
    @SmallTest
    fun test_isRegionDisplayed() {

        //Check Region is displayed
        onView(withId(R.id.regionHeading)).check(matches(isDisplayed()))
            .check(matches(withText(R.string.label_region)))

        onView(withId(R.id.regionView)).check(matches(isDisplayed()))
            .check(matches(withText(getRegion())))
    }

    @Test
    @SmallTest
    fun test_isAreaDisplayed() {

        //Check Area is displayed
        onView(withId(R.id.areaHeading)).check(matches(isDisplayed()))
            .check(matches(withText(R.string.label_area)))

        onView(withId(R.id.areaView)).check(matches(isDisplayed()))
            .check(matches(withText(getArea().toString())))
    }

    @Test
    @SmallTest
    fun test_isPopulationDisplayed() {

        //Check population is displayed
        onView(withId(R.id.populationHeading)).check(matches(isDisplayed()))
            .check(matches(withText(R.string.label_population)))

        onView(withId(R.id.populationView)).check(matches(isDisplayed()))
            .check(matches(withText(getPopulation().toString())))
    }

    @Test
    @SmallTest
    fun test_isAdministeredDisplayed() {

        //Check administered is displayed
        onView(withId(R.id.administeredHeading)).check(matches(isDisplayed()))
            .check(matches(withText(R.string.label_administered)))

        onView(withId(R.id.administeredView)).check(matches(isDisplayed()))
            .check(matches(withText(getAdministered().toString())))
    }

    @Test
    @SmallTest
    fun test_isPeopleVaccinatedDisplayed() {

        //Check people Vaccinated is displayed
        onView(withId(R.id.peopleVaccinatedHeading)).check(matches(isDisplayed()))
            .check(matches(withText(R.string.label_people_vaccinated)))

        onView(withId(R.id.peopleVaccinatedView)).check(matches(isDisplayed()))
            .check(matches(withText(getPeopleVaccinated().toString())))
    }
}