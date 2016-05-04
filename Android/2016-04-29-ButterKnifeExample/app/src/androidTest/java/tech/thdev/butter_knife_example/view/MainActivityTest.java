package tech.thdev.butter_knife_example.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.thdev.butter_knife_example.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by Tae-hwan on 5/4/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testButtonOne() throws Exception {
        onView(withId(R.id.btn_one)).perform(click());

        onView(withId(R.id.text_one)).check(matches(withText("One Message")));

        onView(withText("Button one")).perform(longClick());
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("One Message long click")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testEditTextMessageIntent() throws Exception {
        // Type text and then press the button.
        onView(withId(R.id.et_message)).perform(clearText());
        onView(withId(R.id.et_message)).perform(typeText("Change Activity ..."), closeSoftKeyboard());

        onView(withId(R.id.btn_two)).perform(click());

        onView(withId(R.id.tv_message)).check(matches(withText("Change Activity ...")));
    }
}