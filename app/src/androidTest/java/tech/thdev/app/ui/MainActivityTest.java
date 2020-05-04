package tech.thdev.app.ui;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.thdev.app.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Before
    public void setUp() {

    }

    @Test
    public void testButtonOne() {
        onView(withId(R.id.btn_sample_one)).perform(click());

        onView(withId(R.id.text_sample_one_message)).check(matches(withText("One Message")));

        onView(withText("Message change example - onClick/onLongClick")).perform(longClick());
        onView(allOf(withId(com.google.android.material.R.id.snackbar_text), withText("One Message long click")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testEditTextMessageIntent() {
        // Type text and then press the button.
        onView(withId(R.id.et_intent_sample)).perform(clearText());
        onView(withId(R.id.et_intent_sample)).perform(typeText("Change Activity ..."), closeSoftKeyboard());

        onView(withId(R.id.btn_intent_example)).perform(click());

        onView(withId(R.id.tv_message)).check(matches(withText("Change Activity ...")));
    }
}