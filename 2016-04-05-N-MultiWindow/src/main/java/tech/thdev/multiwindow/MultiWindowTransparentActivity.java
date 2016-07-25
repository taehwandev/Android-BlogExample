package tech.thdev.multiwindow;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import tech.thdev.multiwindow.base.BaseMultiWindowActivity;

/**
 * Created by Tae-hwan on 4/6/16.
 */
public class MultiWindowTransparentActivity extends BaseMultiWindowActivity {

    LinearLayout llTransparentView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_multi_window;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.multi_window_transparent_example;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        llTransparentView.setVisibility(View.VISIBLE);
    }

    public void onClickTransparentActivity(View view) {
        startActivity(new Intent(this, TransparentActivity.class));
    }

    public void onClickDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.multi_window_example);
        builder.setMessage(R.string.multi_window_message);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
