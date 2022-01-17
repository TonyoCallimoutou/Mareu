package com.example.mareu.ui.reunion_list;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.mareu.R;
import com.example.mareu.utils.DeleteReunionViewAction;
import com.example.mareu.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class ListReunionActivityTest {

    // This is fixed
    private static int ITEMS_COUNT = 3;

    @Rule
    public ActivityScenarioRule<ListReunionActivity> mActivityTestRule
            = new ActivityScenarioRule<ListReunionActivity>(ListReunionActivity.class);


    @Test
    public void myReunionsList_shouldNotBeEmpty() {
        onView(withId(R.id.container))
                .check(matches(hasMinimumChildCount(1)));

    }

    @Test
    public void myReunionsList_deleteAction_shouldRemoveItem() {

        onView(withId(R.id.recyclerview_reunion_fragment))
                .perform(actionOnItemAtPosition(1, new DeleteReunionViewAction()));


        onView(withId(R.id.recyclerview_reunion_fragment))
                .check(new RecyclerViewItemCountAssertion(ITEMS_COUNT - 1));

        ITEMS_COUNT --;


    }


    @Test
    public void myReunionList_ByTime() {
        onView(withId(R.id.spinner_filter))
                .perform(click());
        onData(Matchers.allOf(Matchers.is(Matchers.instanceOf(String.class)), Matchers.is("Filter by time")))
                .perform(click());

        onView(withClassName(equalTo(DatePicker.class.getName()))).
                perform(PickerActions.setDate(2021, 11, 28));
        onView(withId(android.R.id.button1)).perform(click());


        onView(withId(R.id.recyclerview_reunion_fragment))
                .check(new RecyclerViewItemCountAssertion(1));


    }

    @Test
    public void myReunionList_ByPlace() {
        onView(withId(R.id.spinner_filter))
                .perform(click());
        onData(Matchers.allOf(Matchers.is(Matchers.instanceOf(String.class)), Matchers.is("Filter by place")))
                .perform(click());

        onData(Matchers.allOf(Matchers.is(Matchers.instanceOf(String.class)), Matchers.is("Salle A")))
                .perform(click());

        onView(withId(R.id.recyclerview_reunion_fragment))
                .check(new RecyclerViewItemCountAssertion(1));

    }

    /**
     * Lancement nouvelle activity
     */
    @Test
    public void myReunionsList_addActivity() {
        onView(withId(R.id.fab_add_reunion))
                .perform(click());

        onView(withId(R.id.create))
                .check(matches(isDisplayed()));
    }

}
