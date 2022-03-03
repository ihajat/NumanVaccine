package com.example.numanvaccine.presentation.common;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

public class MyClickViewAction {

    public static ViewAction click() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void perform(UiController uiController, View v) {
                v.performClick();
                uiController.loopMainThreadUntilIdle();
            }
        };
    }
}