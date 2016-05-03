package tech.thdev.butter_knife_example.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.thdev.butter_knife_example.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.jayway.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by Tae-hwan on 5/3/16.
 */
@RunWith(AndroidJUnit4.class)
public class PhotoRecyclerViewActivityTest {

    @Rule
    public ActivityTestRule<PhotoRecyclerViewActivity> rule = new ActivityTestRule<>(PhotoRecyclerViewActivity.class);

    private PhotoRecyclerViewActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = rule.getActivity();
        await().until(new Runnable() {
            @Override
            public void run() {
                // adapter size > 0
                while (!(activity.adapter.getSize() > 0)) {

                }
            }
        });
    }

    /**
     * Adapter size check
     */
    @Test
    public void testItemLoad() throws Exception {
        if (activity.adapter != null) {
            assertThat(activity.adapter.getSize(), is(greaterThan(100)));
        }
    }

    @Test
    public void testShowBottomSheet() throws Throwable {
        rule.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                activity.photoPresenter.photoItemClick(0);
            }
        });

        onView(withId(R.id.rl_bottom_sheet))
                .check(matches(isDisplayed()));
    }
}