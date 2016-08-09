package tech.thdev.webviewjavascriptinterface.view.main;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.thdev.webviewjavascriptinterface.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Tae-hwan on 8/9/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Rule
    public IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testWebToAndroidScriptCall() throws Exception {
        onView(withId(R.id.et_url)).perform(clearText());
        onView(withId(R.id.et_url)).perform(typeText(MainFragment.DEFAULT_URL)).perform(ViewActions.pressImeActionButton());

        onWebView()
                // Find the input element by ID
                .withElement(findElement(Locator.ID, "search_keyword"))
                // Clear previous input
                .perform(clearElement())
                // Enter text into the input element
                .perform(DriverAtoms.webKeys("EditText Keyword change...."))
                // Find the submit button
                .withElement(findElement(Locator.ID, "updateKeywordBtn"))
                // Simulate a click via javascript
                .perform(webClick());

        onView(withId(R.id.et_keyword)).check(matches(withText("EditText Keyword change....")));
    }

    @Test
    public void testAndroidToWebScriptCall() throws Exception {
        onView(withId(R.id.et_url)).perform(clearText());
        onView(withId(R.id.et_url)).perform(typeText(MainFragment.DEFAULT_URL)).perform(ViewActions.pressImeActionButton());

        onView(withId(R.id.et_keyword)).perform(clearText()).perform(typeText("WebView text change event..."));
        onView(withId(R.id.btn_search)).perform(click());

        onWebView()
                // Find the input element by ID
                .withElement(findElement(Locator.ID, "search_keyword"))
                .check(webMatches(getText(), containsString("WebView text change event...")));
    }
}