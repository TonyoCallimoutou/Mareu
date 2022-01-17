package com.example.mareu.ui.reunion_list;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.mareu.R;
import com.example.mareu.model.Place;
import com.example.mareu.service.FakeApiGenerator;
import com.example.mareu.utils.DeleteReunionViewAction;
import com.example.mareu.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class AddReunionActivityTest {

    // This is fixed
    private static int ITEMS_COUNT = 3;


    @Rule
    public ActivityScenarioRule<ListReunionActivity> mActivityTestRule = new ActivityScenarioRule<ListReunionActivity>(ListReunionActivity.class);

    @Before
    public void removeAllReunion() {

        if (ITEMS_COUNT != 0) {
            for (int i=0; i < ITEMS_COUNT ; i++) {
                onView(withId(R.id.recyclerview_reunion_fragment))
                        .perform(actionOnItemAtPosition(0, new DeleteReunionViewAction()));
            }
        }

        onView(withId(R.id.recyclerview_reunion_fragment))
                .check(new RecyclerViewItemCountAssertion(0));

        ITEMS_COUNT = 0;

    }


    @Test
    public void myReunionsList_addActivity_EmptyInformation() {
        onView(withId(R.id.fab_add_reunion))
                .perform(click());

        onView(withId(R.id.create))
                .perform(click());

        onView(withId(R.id.recyclerview_reunion_fragment))
                .check(new RecyclerViewItemCountAssertion(ITEMS_COUNT + 1));

        ITEMS_COUNT++;

    }

    @Test
    public void myReunionsList_addActivity_PlaceInformation() {
        onView(withId(R.id.fab_add_reunion))
                .perform(click());

        onView(withId(R.id.spinner_place))
                .perform(click());
        onData(allOf(is(instanceOf(Place.class)), is(FakeApiGenerator.FAKE_PLACE.get(3))))
                .perform(click());

        onView(withId(R.id.create))
                .perform(click());

        onView(withId(R.id.recyclerview_reunion_fragment))
                .check(new RecyclerViewItemCountAssertion(ITEMS_COUNT + 1));

        ITEMS_COUNT++;


        onView(withId(R.id.item_list_place))
                .check(matches(withText("Salle D")));

    }

    @Test
    public void myReunionsList_addActivity_TopicInformation() {
        onView(withId(R.id.fab_add_reunion))
                .perform(click());

        onView(withId(R.id.input_topic_edit_text)).perform(typeText("Topic test"));


        onView(withId(R.id.create))
                .perform(click());
        onView(withId(R.id.recyclerview_reunion_fragment))
                .check(new RecyclerViewItemCountAssertion(1));
        ITEMS_COUNT++;


        onView(withId(R.id.item_list_topic))
                .check(matches(withText("Topic test")));
    }

    @Test
    public void myReunionList_addActivity_dateInformation() {
        onView(withId(R.id.fab_add_reunion))
                .perform(click());

        onView(withId(R.id.container_add_fragment))
                .perform(swipeLeft());

        onView(withId(R.id.picker_date))
                .perform(PickerActions.setDate(2000,12,12));
        onView(withId(R.id.picker_time))
                .perform(PickerActions.setTime(20,20));

        onView(withId(R.id.create))
                .perform(click());
        onView(withId(R.id.recyclerview_reunion_fragment))
                .check(new RecyclerViewItemCountAssertion(1));
        ITEMS_COUNT++;


        onView(withId(R.id.item_list_time))
                .check(matches(withText("2000/12/12 at 20:20")));
    }

    @Test
    public void myReunionList_addActivity_ParticipantsInformation() {
        onView(withId(R.id.fab_add_reunion))
                .perform(click());

        onView(withId(R.id.container_add_fragment))
                .perform(swipeLeft());
        onView(withId(R.id.container_add_fragment))
                .perform(swipeLeft());

        onData(Matchers.allOf(Matchers.is(Matchers.instanceOf(String.class)), Matchers.is("alex@lamazone.com")))
                .perform(click());
        onData(Matchers.allOf(Matchers.is(Matchers.instanceOf(String.class)), Matchers.is("viviane@lamazone.com")))
                .perform(click());

        onView(withId(R.id.create))
                .perform(click());
        onView(withId(R.id.recyclerview_reunion_fragment))
                .check(new RecyclerViewItemCountAssertion(1));
        ITEMS_COUNT++;


        onView(withId(R.id.item_list_participant))
                .check(matches(withText("[alex@lamazone.com, viviane@lamazone.com]")));

    }



}
