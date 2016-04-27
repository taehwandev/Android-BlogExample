package tech.thdev.butter_knife_example.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import tech.thdev.butter_knife_example.R;

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

    @Optional
    @OnClick(R.id.fab)
    public void onMaybeFabClicked(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
