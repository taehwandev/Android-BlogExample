package tech.thdev.bottom_sheet.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.thdev.bottom_sheet.R;

/**
 * Created by Tae-hwan on 4/26/16.
 * <p/>
 * Base Toolbar Activity
 * <p/>
 * ButterKnife bind
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        ButterKnife.bind(this);

        setToolBar();
        onCreate();
    }

    protected void setToolBar() {
        if (toolBar != null) {
            if (getToolbarTitle() > 0) {
                toolBar.setTitle(getToolbarTitle());
            }
            setSupportActionBar(toolBar);
        }
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void onCreate();

    protected
    @StringRes
    int getToolbarTitle() {
        return 0;
    }
}
