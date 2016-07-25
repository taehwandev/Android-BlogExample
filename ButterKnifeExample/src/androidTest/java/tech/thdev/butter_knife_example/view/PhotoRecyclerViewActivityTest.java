package tech.thdev.butter_knife_example.view;

import android.support.design.widget.BottomSheetBehavior;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by Tae-hwan on 5/3/16.
 */
@RunWith(AndroidJUnit4.class)
public class PhotoRecyclerViewActivityTest {

    // create  a signal to let us know when our task is done.
    private final CountDownLatch signal = new CountDownLatch(1);

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
            assertThat(activity.adapter.getSize(), is(greaterThan(10)));
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

        signal.await(1, TimeUnit.SECONDS);

        activity.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        signal.await(1, TimeUnit.SECONDS);

        activity.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}