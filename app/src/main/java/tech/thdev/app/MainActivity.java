package tech.thdev.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.OnClick;
import tech.thdev.app.view.basic.BasicBottomSheetActivity;
import tech.thdev.app.view.recycler.RecyclerBottomSheetActivity;
import tech.thdev.base.view.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.btn_basic_bottom_sheet)
    public void onClickBasicBottomSheet() {
        startActivity(new Intent(this, BasicBottomSheetActivity.class));
    }

    @OnClick(R.id.btn_recycler_bottom_sheet)
    public void onClickRecyclerBottomSheet() {
        startActivity(new Intent(this, RecyclerBottomSheetActivity.class));
    }
}
