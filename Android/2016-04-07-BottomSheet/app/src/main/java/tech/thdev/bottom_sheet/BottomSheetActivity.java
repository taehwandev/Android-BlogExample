package tech.thdev.bottom_sheet;

import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.OnClick;
import tech.thdev.bottom_sheet.base.BaseActivity;

/**
 * Created by Tae-hwan on 4/6/16.
 */
public class BottomSheetActivity extends BaseActivity {

    @Bind(R.id.rl_bottom_sheet)
    RelativeLayout rlBottomSheet;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_bottom_sheet);
    }

    @Override
    protected void onCreate() {

    }

    @OnClick(R.id.fab)
    public void onClickFloatingActionButton(View view) {
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setHideable(true);
    }
}
