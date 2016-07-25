package tech.thdev.multiwindow.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.thdev.multiwindow.R;

/**
 * Created by taehwankwon on 4/4/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        ButterKnife.bind(this);

        setToolBar();
        onCreate();
    }

    /**
     * ToolBar init
     */
    protected void setToolBar() {
        if (toolbar != null) {
            if (getToolbarTitle() > 0) {
                toolbar.setTitle(getToolbarTitle());
            }
            setSupportActionBar(toolbar);
        }
    }

    @StringRes
    protected int getToolbarTitle() {
        return 0;
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void onCreate();
}
