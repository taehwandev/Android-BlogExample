package tech.thdev.app.ui.main

import android.content.Intent
import androidx.test.espresso.web.assertion.WebViewAssertions
import androidx.test.espresso.web.model.Atoms
import androidx.test.espresso.web.sugar.Web
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.thdev.app.constant.KEY_INTENT_URL

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun testWebView() {
        rule.launchActivity(withWebFormIntent("http://thdev.tech/"))

        Web.onWebView()
            .withNoTimeout()
            .check(
                WebViewAssertions.webMatches(
                    Atoms.getCurrentUrl(),
                    Matchers.containsString("http://thdev.tech/")
                )
            )
    }

    private fun withWebFormIntent(url: String): Intent {
        val intent = Intent()
        intent.putExtra(KEY_INTENT_URL, url)
        return intent
    }
}