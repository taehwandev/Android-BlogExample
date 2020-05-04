package tech.thdev.app.ui.photo;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PhotoRecyclerViewActivityTest {

    // create  a signal to let us know when our task is done.
    private final CountDownLatch signal = new CountDownLatch(1);

    @Rule
    public ActivityTestRule<PhotoRecyclerViewActivity> rule = new ActivityTestRule<>(PhotoRecyclerViewActivity.class, false, true);

    private PhotoRecyclerViewActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = rule.getActivity();
        await().until(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                // adapter size > 0
                while (!(activity.adapter.getItemCount() > 0)) {

                }
                return true;
            }
        });
    }

    /**
     * Adapter size check
     */
    @Test
    public void testItemLoad() throws Exception {
        if (activity.adapter != null) {
            assertThat(activity.adapter.getItemCount(), is(greaterThan(10)));
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