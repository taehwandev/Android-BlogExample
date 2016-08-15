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
import static android.support.test.espresso.web.assertion.WebViewAssertions.webContent;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.matcher.DomMatchers.hasElementWithId;
import static android.support.test.espresso.web.model.Atoms.castOrDie;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static android.support.test.espresso.web.model.Atoms.script;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Tae-hwan on 8/9/16.
 * <p>
 * - https://android.googlesource.com/platform/frameworks/testing/+/android-support-test/espresso/sample/src/androidTest/java/android/support/test/testapp/WebViewTest.java
 * - http://blog.egorand.me/testing-runtime-permissions-lessons-learned/
 */
@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    private static final String ANDROID_SCRIPT_CALL = "EditText keyword change";
    private static final String JAVASCRIPT_CALL = "Web text change";

    @Rule
    public IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testHasElement() throws Exception {
        waitWebViewLoad();

        onWebView()
                .check(webContent(hasElementWithId("search_keyword")))
                .check(webContent(hasElementWithId("updateKeywordBtn")))
                .check(webContent(hasElementWithId("showAlertBtn")))
                .check(webContent(hasElementWithId("message")));
    }

    @Test
    public void testWebToAndroidScriptCall() throws Exception {
        String go = MainFragment.DEFAULT_URL;

        onView(withId(R.id.et_url)).perform(clearText());
        onView(withId(R.id.et_url)).perform(typeText(go)).perform(ViewActions.pressImeActionButton());

        waitWebViewLoad();

        onWebView()
                // Find the search keyword element by ID
                .withElement(findElement(Locator.ID, "search_keyword"))
                // Clear previous input
                .perform(clearElement())
                // Enter text into the input element
                .perform(DriverAtoms.webKeys(ANDROID_SCRIPT_CALL))
                // Value check. script getValue 'search_keyword'
                .check(webMatches(script("return document.getElementById('search_keyword').value", castOrDie(String.class)), containsString(ANDROID_SCRIPT_CALL)))
                // Find the submit button
                .withElement(findElement(Locator.ID, "updateKeywordBtn"))
                // Simulate a click via javascript
                .perform(webClick());

        onView(withId(R.id.et_keyword)).check(matches(withText(ANDROID_SCRIPT_CALL)));
    }

    @Test
    public void testWebViewUpdateElementByInputText() throws Exception {
        String go = MainFragment.DEFAULT_URL;

        onView(withId(R.id.et_url)).perform(clearText());
        onView(withId(R.id.et_url)).perform(typeText(go)).perform(ViewActions.pressImeActionButton());

        waitWebViewLoad();

        onView(withId(R.id.et_keyword)).perform(clearText()).perform(typeText(JAVASCRIPT_CALL));
        onView(withId(R.id.btn_search)).perform(click());

        onWebView()
                //I use this to allow all needed time to WebView to load
                .withNoTimeout()
                // Find the message element by ID
                .check(webContent(hasElementWithId("search_keyword")))
                .check(webMatches(script("return document.getElementById('search_keyword').value", castOrDie(String.class)), containsString(JAVASCRIPT_CALL)));
    }

    @Test
    public void testWebViewUpdateElementByDisplayed() throws Exception {
        String go = MainFragment.DEFAULT_URL;

        onView(withId(R.id.et_url)).perform(clearText());
        onView(withId(R.id.et_url)).perform(typeText(go)).perform(ViewActions.pressImeActionButton());

        waitWebViewLoad();

        onView(withId(R.id.et_keyword)).perform(clearText()).perform(typeText(JAVASCRIPT_CALL));
        onView(withId(R.id.btn_search)).perform(click());

        onWebView()
                // Find the message element by ID
                .withElement(findElement(Locator.ID, "message"))
                // Verify that the text is displayed
                .check(webMatches(getText(), containsString(JAVASCRIPT_CALL)));
    }

    @Test
    public void testCallWebJavascript() throws Throwable {
        waitWebViewLoad();

        onWebView()
                // Find the search keyword element by ID
                .withElement(findElement(Locator.ID, "search_keyword"))
                // Clear previous input
                .perform(clearElement())
                // Enter text into the input element
                .perform(DriverAtoms.webKeys(ANDROID_SCRIPT_CALL))
                .perform(script("updateKeyword();"));

        onView(withId(R.id.et_keyword)).check(matches(withText(ANDROID_SCRIPT_CALL)));
    }

    @Test
    public void testChangeWebViewTest() throws Exception {
        waitWebViewLoad();

        onWebView()
                // Find the search keyword element by ID
                .withElement(findElement(Locator.ID, "url"))
                // Clear previous input
                .perform(clearElement())
                // Enter text into the input element
                .perform(DriverAtoms.webKeys("http://google.com/"))
                // Find the submit button
                .withElement(findElement(Locator.ID, "changeWebView"))
                // Simulate a click via javascript
                .perform(webClick());

        onWebView()
                .withNoTimeout()
                .check(webMatches(getCurrentUrl(), containsString("https://www.google")));
    }

    /**
     * WebView load finish check.
     */
    private void waitWebViewLoad() throws Exception {
        onWebView()
                .withNoTimeout()
                // Check current url
                .check(webMatches(getCurrentUrl(), containsString(MainFragment.DEFAULT_URL)));
    }
}