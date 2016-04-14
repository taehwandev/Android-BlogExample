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

import butterknife.Bind;
import butterknife.OnClick;
import tech.thdev.multiwindow.FreeformModeActivity;
import tech.thdev.multiwindow.MultiWindowActivity;
import tech.thdev.multiwindow.MultiWindowTransparentActivity;
import tech.thdev.multiwindow.R;
import tech.thdev.multiwindow.adapter.MultiWindowAdapter;

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
public abstract class BaseMultiWindowActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private MultiWindowAdapter multiWindowAdapter;

    @Override
    protected void onCreate() {
        multiWindowAdapter = new MultiWindowAdapter(this, new ArrayList<String>());
        recyclerView.setAdapter(multiWindowAdapter);

        if (multiWindowAdapter != null) {
            multiWindowAdapter.addItem("onCreate() isInMultiWindowMode " + isInMultiWindowMode(), true);
        }
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
                // TODO Android N Preview 2 is not walking.
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

        if (multiWindowAdapter != null) {
            multiWindowAdapter.addItem("onResume() isInMultiWindowMode " + isInMultiWindowMode(), true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (multiWindowAdapter != null) {
            multiWindowAdapter.addItem("onPause() isInMultiWindowMode " + isInMultiWindowMode(), true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (multiWindowAdapter != null) {
            multiWindowAdapter.addItem("onStart() isInMultiWindowMode " + isInMultiWindowMode(), true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (multiWindowAdapter != null) {
            multiWindowAdapter.addItem("onStop() isInMultiWindowMode " + isInMultiWindowMode(), true);
        }
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
        if (multiWindowAdapter != null) {
            multiWindowAdapter.addItem("onConfigurationChanged() isInMultiWindowMode " + isInMultiWindowMode() + ", newConfig " + newConfig.orientation, true);
        }
    }
}
