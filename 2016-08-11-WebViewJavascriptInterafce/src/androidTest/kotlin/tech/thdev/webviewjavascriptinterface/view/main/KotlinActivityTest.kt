package tech.thdev.webviewjavascriptinterface.view.main

import android.content.Intent
import android.support.test.espresso.web.assertion.WebViewAssertions
import android.support.test.espresso.web.model.Atoms
import android.support.test.espresso.web.sugar.Web
import android.support.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import tech.thdev.webviewjavascriptinterface.constant.Constant

/**
 * Created by tae-hwan on 8/11/16.
 */
class KotlinActivityTest {

    @get:Rule
    val rule: ActivityTestRule<KotlinActivity> = ActivityTestRule(KotlinActivity::class.java, false, false)

    @Test
    fun testWebView() {
        rule.launchActivity(withWebFormIntent("http://thdev.tech"))

        Web.onWebView()
                .withNoTimeout()
                .check(WebViewAssertions.webMatches(Atoms.getCurrentUrl(), Matchers.containsString("http://thdev.tech")))
    }

    private fun withWebFormIntent(url: String): Intent {
        val intent = Intent()
        intent.putExtra(Constant.KEY_INTENT_URL, url)
        return intent
    }
}