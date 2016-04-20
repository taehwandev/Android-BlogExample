package tech.thdev.androidtestexample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Tae-hwan on 4/20/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MainActivity activity;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        activity = activityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testNullCheck() throws Exception {
        EditText editText = (EditText) activity.findViewById(R.id.et_message);
        assertNotNull(editText);
    }

    @Test
    public void setEqualsText() throws Exception {
        onView(withId(R.id.btn_set_message)).perform(click());

        EditText editText = (EditText) activity.findViewById(R.id.et_message);
        assertNotNull(editText);
        assertEquals(activity.getString(R.string.instrumentation_api_title), editText.getText().toString());
//        assertEquals("Text example", editText.getText().toString());
    }

    @Test
    public void testButtonShouldUpdateText() throws Exception {
        onView(withId(R.id.btn_set_message)).perform(click());
        onView(withId(R.id.et_message)).check(matches(withText(activity.getString(R.string.instrumentation_api_title))));
    }

    @Test
    public void testChangeTextToSecondActivity() throws Exception {
        // Type text and then press the button.
        onView(withId(R.id.et_message)).perform(clearText());
        onView(withId(R.id.et_message)).perform(typeText("Change Activity ..."), closeSoftKeyboard());

        onView(withId(R.id.btn_second_activity)).perform(click());

        onView(withId(R.id.tv_message)).check(matches(withText("Change Activity ...")));
    }

    @Test
    public void testWebView() throws Exception {
        onView(withId(R.id.btn_web_view)).perform(click());
        intended(toPackage());
    }
}