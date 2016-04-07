package tech.thdev.multiwindow.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import tech.thdev.multiwindow.R;

/**
 * Created by taehwankwon on 4/4/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable @Bind(R.id.toolbar)
    Toolbar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
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

    protected abstract void setContentView();

    protected abstract void onCreate();

    protected @StringRes int getToolbarTitle() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
