package com.example.numanvaccine.presentation.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.numanvaccine.R
import com.example.numanvaccine.presentation.common.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class TitleFragmentTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    @SmallTest
    fun test_isWelcomeDisplayed() {

        //Check Welcome Home Screen is displayed
        onView(withId(R.id.txt_welcome)).check(matches(isDisplayed()))
            .check(matches(withText(R.string.welcome)))
    }

    @Test
    @SmallTest
    fun test_isDescriptionDisplayed() {

        //Check Description Home Screen is displayed
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()))
            .check(matches(withText(R.string.welcome)))
    }
}