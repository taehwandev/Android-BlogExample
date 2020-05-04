package tech.thdev.app.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import tech.thdev.app.R;

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
    @SuppressLint("ResourceType")
    protected void setToolBar() {
        if (toolbar != null) {
            toolbar.setTitle(getToolbarTitle());
            setSupportActionBar(toolbar);
        }
    }

    @StringRes
    protected int getToolbarTitle() {
        return R.string.app_name;
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
