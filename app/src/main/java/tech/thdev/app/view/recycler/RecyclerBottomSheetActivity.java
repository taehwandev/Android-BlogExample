package tech.thdev.app.view.recycler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import tech.thdev.app.R;
import tech.thdev.base.view.BaseActivity;

/**
 * Created by tae-hwan on 07/12/2016.
 */

public class RecyclerBottomSheetActivity extends BaseActivity {

    @BindView(R.id.rl_bottom_sheet)
    RelativeLayout rlBottomSheet;

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        setTitle(R.string.recycler_sample_bottom_title);
                        break;

                    default:
                        setTitle(R.string.label_recycler_activity);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @OnClick(R.id.btn_show)
    public void onClickShow() {
        // height 만큼 화면에 노출
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @OnClick(R.id.btn_hidden)
    public void onClickHidden() {
        // 숨김 처리
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @OnClick(R.id.btn_expanded)
    public void onClickExpanded() {
        // 확장
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
