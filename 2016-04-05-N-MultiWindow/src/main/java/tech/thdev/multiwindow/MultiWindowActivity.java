package tech.thdev.multiwindow;

import tech.thdev.multiwindow.base.BaseMultiWindowActivity;

/**
 * Created by taehwankwon on 4/4/16.
 */
public class MultiWindowActivity extends BaseMultiWindowActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_multi_window;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.multi_window_example;
    }
}
