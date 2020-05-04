package tech.thdev.app.ui.photosearch;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import kotlin.jvm.functions.Function2;
import tech.thdev.app.R;
import tech.thdev.app.base.BaseActivity;
import tech.thdev.app.network.RetrofitPhoto;
import tech.thdev.app.network.domain.Photo;
import tech.thdev.app.ui.photo.adapter.PhotoViewHolder;
import tech.thdev.simpleadapter.SimpleRecyclerViewAdapter;
import tech.thdev.simpleadapter.holder.BaseViewHolder;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoSearchRecyclerViewActivity extends BaseActivity implements PhotoSearchPresenterView {

    private static final int DEFAULT_PAGE = 1;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.rl_bottom_sheet)
    ConstraintLayout rlBottomSheet;

    @BindView(R.id.image)
    AppCompatImageView imageView;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.et_search_keyword)
    AppCompatEditText etSearchKeyword;

    protected PhotoSearchPresenter photoSearchPresenter;
    protected SimpleRecyclerViewAdapter adapter;

    private float tmpBottomSlideOffset;
    protected BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_photo_recycler_view;
    }

    @Override
    protected void onCreate() {
        etSearchKeyword.setVisibility(View.VISIBLE);
        etSearchKeyword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                photoSearchPresenter.loadSearchPhotos(10, DEFAULT_PAGE, etSearchKeyword.getText().toString());
                return true;
            }
            return false;
        });

        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet);
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200.f, getResources().getDisplayMetrics()));
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback);

        adapter = getSimpleAdapter();

        photoSearchPresenter = new PhotoSearchPresenter(this, RetrofitPhoto.getRetrofitPhoto(), adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);

        photoSearchPresenter.loadSearchPhotos(10, DEFAULT_PAGE, "paris");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bottomSheetBehavior.removeBottomSheetCallback(bottomSheetCallback);
    }

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
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
    };

    @Override
    public void onRefresh() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBottomSheetShow(Photo photo) {
        Glide.with(this)
                .load(String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", String.valueOf(photo.farm), photo.server, photo.id, photo.secret))
                .centerCrop()
                .into(imageView);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private SimpleRecyclerViewAdapter getSimpleAdapter() {
        return new SimpleRecyclerViewAdapter(new Function2<ViewGroup, Integer, BaseViewHolder<?>>() {
            @Override
            public BaseViewHolder<?> invoke(ViewGroup viewGroup, Integer integer) {
                return new PhotoViewHolder(viewGroup, new tech.thdev.app.ui.photo.adapter.OnItemClickListener() {
                    @Override
                    public void onClickItem(int adapterPosition) {
                        photoSearchPresenter.photoItemClick(adapterPosition);
                    }
                });
            }
        });
    }
}
