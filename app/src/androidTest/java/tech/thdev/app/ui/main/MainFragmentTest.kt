package tech.thdev.app.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.web.assertion.WebViewAssertions.webContent
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.matcher.DomMatchers.hasElementWithId
import androidx.test.espresso.web.model.Atoms.*
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.thdev.app.R

/**
 * Created by Tae-hwan on 8/9/16.
 *
 *
 * - https://android.googlesource.com/platform/frameworks/testing/+/android-support-test/espresso/sample/src/androidTest/java/android/support/test/testapp/WebViewTest.java
 * - http://blog.egorand.me/testing-runtime-permissions-lessons-learned/
 */
@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @get:Rule
    var activityRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)


    @Test
    @Throws(Exception::class)
    fun testHasElement() {
        waitWebViewLoad()
        onWebView()
            .check(webContent(hasElementWithId("search_keyword")))
            .check(webContent(hasElementWithId("updateKeywordBtn")))
            .check(webContent(hasElementWithId("showAlertBtn")))
            .check(webContent(hasElementWithId("message")))
    }

    @Test
    @Throws(Exception::class)
    fun testWebToAndroidScriptCall() {
        val go = MainFragment.DEFAULT_URL
        onView(withId(R.id.et_url)).perform(clearText())
        onView(withId(R.id.et_url)).perform(typeText(go))
            .perform(ViewActions.pressImeActionButton())
        waitWebViewLoad()
        onWebView() // Find the search keyword element by ID
            .withElement(findElement(Locator.ID, "search_keyword")) // Clear previous input
            .perform(clearElement()) // Enter text into the input element
            .perform(DriverAtoms.webKeys(ANDROID_SCRIPT_CALL)) // Value check. script getValue 'search_keyword'
            .check(
                webMatches(
                    script(
                        "return document.getElementById('search_keyword').value", castOrDie(
                            String::class.java
                        )
                    ), containsString(ANDROID_SCRIPT_CALL)
                )
            ) // Find the submit button
            .withElement(
                findElement(
                    Locator.ID,
                    "updateKeywordBtn"
                )
            ) // Simulate a click via javascript
            .perform(webClick())
        onView(withId(R.id.et_keyword)).check(matches(withText(ANDROID_SCRIPT_CALL)))
    }

    @Test
    @Throws(Exception::class)
    fun testWebViewUpdateElementByInputText() {
        val go = MainFragment.DEFAULT_URL
        onView(withId(R.id.et_url)).perform(clearText())
        onView(withId(R.id.et_url)).perform(typeText(go))
            .perform(ViewActions.pressImeActionButton())
        waitWebViewLoad()
        onView(withId(R.id.et_keyword)).perform(clearText())
            .perform(typeText(JAVASCRIPT_CALL))
        onView(withId(R.id.btn_search)).perform(click())
        onWebView() //I use this to allow all needed time to WebView to load
            .withNoTimeout() // Find the message element by ID
            .check(webContent(hasElementWithId("search_keyword")))
            .check(
                webMatches(
                    script(
                        "return document.getElementById('search_keyword').value", castOrDie(
                            String::class.java
                        )
                    ), containsString(JAVASCRIPT_CALL)
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun testWebViewUpdateElementByDisplayed() {
        val go = MainFragment.DEFAULT_URL
        onView(withId(R.id.et_url)).perform(clearText())
        onView(withId(R.id.et_url)).perform(typeText(go))
            .perform(ViewActions.pressImeActionButton())
        waitWebViewLoad()
        onView(withId(R.id.et_keyword)).perform(clearText())
            .perform(typeText(JAVASCRIPT_CALL))
        onView(withId(R.id.btn_search)).perform(click())
        onWebView() // Find the message element by ID
            .withElement(findElement(Locator.ID, "message")) // Verify that the text is displayed
            .check(
                webMatches(
                    getText(),
                    containsString(JAVASCRIPT_CALL)
                )
            )
    }

    @Test
    @Throws(Throwable::class)
    fun testCallWebJavascript() {
        waitWebViewLoad()
        onWebView() // Find the search keyword element by ID
            .withElement(findElement(Locator.ID, "search_keyword")) // Clear previous input
            .perform(clearElement()) // Enter text into the input element
            .perform(DriverAtoms.webKeys(ANDROID_SCRIPT_CALL))
            .perform(script("updateKeyword();"))
        onView(withId(R.id.et_keyword)).check(matches(withText(ANDROID_SCRIPT_CALL)))
    }

    @Test
    @Throws(Exception::class)
    fun testChangeWebViewTest() {
        waitWebViewLoad()
        onWebView() // Find the search keyword element by ID
            .withElement(findElement(Locator.ID, "url")) // Clear previous input
            .perform(clearElement()) // Enter text into the input element
            .perform(DriverAtoms.webKeys("http://google.com/")) // Find the submit button
            .withElement(
                findElement(
                    Locator.ID,
                    "changeWebView"
                )
            ) // Simulate a click via javascript
            .perform(webClick())
        onWebView()
            .withNoTimeout()
            .check(webMatches(getCurrentUrl(), containsString("https://www.google")))
    }

    /**
     * WebView load finish check.
     */
    @Throws(Exception::class)
    private fun waitWebViewLoad() {
        onWebView()
            .withNoTimeout() // Check current url
            .check(webMatches(getCurrentUrl(), containsString(MainFragment.DEFAULT_URL)))
    }

    companion object {
        private const val ANDROID_SCRIPT_CALL = "EditText keyword change"
        private const val JAVASCRIPT_CALL = "Web text change"
    }
}