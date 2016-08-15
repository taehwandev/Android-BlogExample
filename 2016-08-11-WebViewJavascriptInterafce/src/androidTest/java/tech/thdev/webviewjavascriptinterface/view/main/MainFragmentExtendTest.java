package tech.thdev.webviewjavascriptinterface.view.main;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.web.deps.guava.collect.Lists;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.model.Atoms.castOrDie;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static android.support.test.espresso.web.model.Atoms.script;
import static android.support.test.espresso.web.model.Atoms.scriptWithArgs;
import static android.support.test.espresso.web.model.Atoms.transform;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * Created by tae-hwan on 8/15/16.
 */
public class MainFragmentExtendTest {

    private UiDevice device;

    @Rule
    public IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testShowAlertDialog() throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        waitWebViewLoad(MainFragment.DEFAULT_URL);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Wait second
                    signal.await(1, TimeUnit.SECONDS);
                    // Find ok button and click
                    assertViewWithTextIsVisible(device, rule.getActivity().getString(android.R.string.ok));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // WebView show alert dialog
        onWebView().withElement(findElement(Locator.ID, "showAlertBtn"))
                .perform(webClick());
    }

    @Test
    public void testJavascriptEvaluation() throws Throwable {
        waitWebViewLoad(MainFragment.DEFAULT_URL);

        String script = "function() { return arguments[0] }";

        onWebView()
                .check(webMatches(transform(scriptWithArgs(script,
                        Lists.newArrayList("String")), castOrDie(String.class)), is("String")));


        // Google Testing code
        // Writing using functions is nice for re-usable snippits of logic that vary by their
        // arguments.
        String accumulateFn = "function() {"
                + "  var initial = arguments[0];"
                + "  for (var i = 1; i < arguments.length; i++) { "
                + "    initial += arguments[i];"
                + "  }"
                + "  return initial;"
                + "}";
        onWebView()
                .check(webMatches(transform(scriptWithArgs(accumulateFn,
                        Lists.newArrayList(1, 2, 42, 7)),
                        castOrDie(Integer.class)),
                        is(52)));


        String alert = "function() { alert(arguments[0]) }";

        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Wait second
                    signal.await(1, TimeUnit.SECONDS);
                    // Find ok button and click
                    assertViewWithTextIsVisible(device, rule.getActivity().getString(android.R.string.ok));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        onWebView()
                .perform(script(alert));
    }

    /**
     * WebView load finish check.
     */
    private void waitWebViewLoad(String containsUrl) throws Exception {
        onWebView()
                .withNoTimeout()
                // Check current url
                .check(webMatches(getCurrentUrl(), containsString(containsUrl)));
    }

    private void assertViewWithTextIsVisible(UiDevice device, String text) throws UiObjectNotFoundException {
        UiObject allowButton = device.findObject(new UiSelector().text(text));
        if (!allowButton.exists()) {
            throw new AssertionError("View with text <" + text + "> not found!");
        }
        allowButton.click();
    }
}