package com.example.numanvaccine.presentation.common;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.BoundedMatcher;

import com.example.numanvaccine.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ClickOnImageView implements ViewAction {
    ViewAction click = MyClickViewAction.click();

    @Override
    public Matcher<View> getConstraints() {
        return click.getConstraints();
    }

    @Override
    public String getDescription() {
        return " click on custom image view";
    }

    @Override
    public void perform(UiController uiController, View view) {
        click.perform(uiController, view.findViewById(R.id.favouriteImageView));
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }
};


