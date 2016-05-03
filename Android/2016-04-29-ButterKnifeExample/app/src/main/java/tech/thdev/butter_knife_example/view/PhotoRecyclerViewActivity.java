package tech.thdev.butter_knife_example.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import tech.thdev.butter_knife_example.R;
import tech.thdev.butter_knife_example.adapter.PhotoRecyclerAdapter;
import tech.thdev.butter_knife_example.base.BaseActivity;
import tech.thdev.butter_knife_example.listener.OnRecyclerItemClickListener;
import tech.thdev.butter_knife_example.network.RetrofitPhoto;
import tech.thdev.butter_knife_example.network.domain.Photo;
import tech.thdev.butter_knife_example.presenter.PhotoPresenter;
import tech.thdev.butter_knife_example.presenter.view.PhotoPresenterView;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoRecyclerViewActivity extends BaseActivity implements PhotoPresenterView {

    private static final int DEFAULT_PAGE = 1;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.rl_bottom_sheet)
    RelativeLayout rlBottomSheet;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    protected PhotoPresenter photoPresenter;
    protected PhotoRecyclerAdapter adapter;

    private float tmpBottomSlideOffset;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_photo_recycler_view;
    }

    @Override
    protected void onCreate() {
        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet);
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200.f, getResources().getDisplayMetrics()));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset > 0.8) {
                    if (tmpBottomSlideOffset > slideOffset) {
                        floatingActionButton.show();

                    } else {
                        floatingActionButton.hide();
                    }

                } else {
                    floatingActionButton.show();
                }

                tmpBottomSlideOffset = slideOffset;
            }
        });

        adapter = new PhotoRecyclerAdapter(this);
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                photoPresenter.photoItemClick(position);
            }
        });

        photoPresenter = new PhotoPresenter(this, RetrofitPhoto.getRetrofitPhoto(), adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        photoPresenter.loadPhotos(DEFAULT_PAGE);
    }

    @Override
    public void onRefresh() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBottomSheetShow(Photo photo) {
        Glide.with(this)
                .load(String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", String.valueOf(photo.farm), photo.server, photo.id, photo.secret))
                .centerCrop()
                .crossFade()
                .into(imageView);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}
