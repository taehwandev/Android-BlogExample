package tech.thdev.androidtestexample.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Tae-hwan on 4/20/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        ButterKnife.bind(this);

        onCreate();
    }

    @LayoutRes
    protected abstract int getContentViewId();

    protected abstract void onCreate();
}
