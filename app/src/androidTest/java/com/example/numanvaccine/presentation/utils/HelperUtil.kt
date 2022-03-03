package com.example.numanvaccine.presentation.utils

import android.view.View
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import com.example.numanvaccine.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import androidx.test.espresso.matcher.ViewMatchers.isRoot


object HelperUtil {
    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) =
            ViewActions.click().perform(uiController, view.findViewById<View>(viewId))
    }

    fun withDrawable(@DrawableRes resourceId: Int, state: Int): Matcher<View> {
        return ImageViewHasDrawableMatcher.hasDrawable(state)
    }

    fun hasItemAtPosition(position: Int, matcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description?) {
                description?.appendText("has item at position $position : ")
                matcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                    ?: return false
                return matcher.matches(viewHolder.itemView)
            }
        }
    }

    fun checkBackground(position: Int, @DrawableRes imageRes: Int, state: Int): ViewInteraction {
        return Espresso.onView(ViewMatchers.withId(R.id.countriesList))
            .check(
                ViewAssertions.matches(
                    hasItemAtPosition(
                        position,
                        ViewMatchers.hasDescendant(withDrawable(imageRes, state))
                    )
                )
            )
    }

    /**
     * Perform action of waiting for a specific time.
     */
    fun waitFor(millis: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}