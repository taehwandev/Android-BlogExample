package tech.thdev.app.view.recycler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import tech.thdev.app.R;
import tech.thdev.app.data.BottomData;
import tech.thdev.app.view.recycler.adapter.RecyclerBottomSheetAdapter;
import tech.thdev.base.view.BaseActivity;

/**
 * Created by tae-hwan on 07/12/2016.
 */

public class RecyclerBottomSheetActivity extends BaseActivity {

    @BindView(R.id.rl_bottom_sheet)
    RelativeLayout rlBottomSheet;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        setSupportActionBar(toolbar);
        setTitle(R.string.label_recycler_activity);

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

        // BottomSheet에 사용할 Adapter을 정의한다
        RecyclerBottomSheetAdapter adapter = new RecyclerBottomSheetAdapter(this);
        adapter.setItem(new BottomData("Facebook", R.drawable.icon_01));
        adapter.setItem(new BottomData("Google", R.drawable.icon_02));
        adapter.setItem(new BottomData("Pinterest", R.drawable.icon_03));
        adapter.setItem(new BottomData("Google+", R.drawable.icon_04));
        adapter.setItem(new BottomData("LinkedIn", R.drawable.icon_05));
        adapter.setItem(new BottomData("Twitter", R.drawable.icon_06));
        recyclerView.setAdapter(adapter);
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
