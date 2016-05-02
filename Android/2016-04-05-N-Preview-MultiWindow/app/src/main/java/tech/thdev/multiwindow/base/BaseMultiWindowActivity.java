package tech.thdev.multiwindow.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import tech.thdev.multiwindow.FreeformModeActivity;
import tech.thdev.multiwindow.MultiWindowActivity;
import tech.thdev.multiwindow.MultiWindowTransparentActivity;
import tech.thdev.multiwindow.R;
import tech.thdev.multiwindow.adapter.MultiWindowAdapter;
import tech.thdev.multiwindow.presenter.MultiWindowPresenter;
import tech.thdev.multiwindow.presenter.view.MultiWindowPresenterView;

/**
 * Created by Tae-hwan on 4/6/16.
 * <p/>
 * Multi Window Base activity
 * <p/>
 * Preview 2 Change log
 * * API Name change list
 * - inMultiWindow -> isInMultiWindowMode
 * - onMultiWindowChanged -> onMultiWindowModeChanged
 */
public abstract class BaseMultiWindowActivity extends BaseActivity implements MultiWindowPresenterView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    protected MultiWindowPresenter multiWindowPresenter;

    @Override
    protected void onCreate() {
        MultiWindowAdapter multiWindowAdapter = new MultiWindowAdapter(this);
        recyclerView.setAdapter(multiWindowAdapter);

        multiWindowPresenter = new MultiWindowPresenter(this, multiWindowAdapter);

        addItem("onCreate() isInMultiWindowMode " + isInMultiWindowMode());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_multi_window, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_multi_window:
                onMultiWindowModeChanged(!isInMultiWindowMode());
                Toast.makeText(this, "MultiWindow " + isInMultiWindowMode(), Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        addItem("onResume() isInMultiWindowMode " + isInMultiWindowMode());
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (multiWindowPresenter != null) {
            multiWindowPresenter.addItem("onPause() isInMultiWindowMode " + isInMultiWindowMode());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        addItem("onStart() isInMultiWindowMode " + isInMultiWindowMode());
    }

    @Override
    protected void onStop() {
        super.onStop();

        addItem("onStop() isInMultiWindowMode " + isInMultiWindowMode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("TAG", "onDestroy() isInMultiWindowMode " + isInMultiWindowMode());
    }

    @OnClick(R.id.btn_freeform_mode_example)
    public void onFreeformModeExample(View view) {
        Intent intent = new Intent(this, FreeformModeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
        startActivity(new Intent(this, FreeformModeActivity.class));
        finish();
    }

    @OnClick(R.id.btn_multi_window_example)
    public void onMultiWindowExample(View view) {
        startActivity(new Intent(this, MultiWindowActivity.class));
        finish();
    }

    @OnClick(R.id.btn_transparent_example)
    public void onTransparentExample(View view) {
        startActivity(new Intent(this, MultiWindowTransparentActivity.class));
        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.SCREENLAYOUT_SIZE_SMALL) {

        }

        addItem("onConfigurationChanged() isInMultiWindowMode " + isInMultiWindowMode() + ", newConfig " + newConfig.orientation);
    }

    private void addItem(String name) {
        if (multiWindowPresenter != null) {
            multiWindowPresenter.addItem(name);
        }
    }

    @Override
    public void onItemAdded(int size) {
        recyclerView.scrollToPosition(size - 1);
    }
}
