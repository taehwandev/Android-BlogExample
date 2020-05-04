package tech.thdev.app.ui.photosearch;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.thdev.app.base.presenter.BasePresenter;
import tech.thdev.app.network.RetrofitPhoto;
import tech.thdev.app.network.domain.Photo;
import tech.thdev.app.network.domain.PhotosPageInfo;
import tech.thdev.app.network.domain.RecentPhotoResponse;
import tech.thdev.simpleadapter.data.source.AdapterRepository;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoSearchPresenter extends BasePresenter<PhotoSearchPresenterView> {

    private AdapterRepository adapterRepository;
    private RetrofitPhoto retrofitPhoto;

    public PhotoSearchPresenter(PhotoSearchPresenterView view, RetrofitPhoto retrofitPhoto, AdapterRepository adapterRepository) {
        super(view);

        this.retrofitPhoto = retrofitPhoto;
        this.adapterRepository = adapterRepository;
    }

    public void loadSearchPhotos(int perPage, int page, String text) {
        Call<RecentPhotoResponse> photos = retrofitPhoto.getSearchPhoto(perPage, page, text);
        photos.enqueue(new Callback<RecentPhotoResponse>() {
            @Override
            public void onResponse(@NotNull Call<RecentPhotoResponse> call, @NotNull Response<RecentPhotoResponse> response) {
                loadImage(response);
            }

            @Override
            public void onFailure(@NotNull Call<RecentPhotoResponse> call, @NotNull Throwable t) {
                failLoadImage();
            }
        });
    }

    private void loadImage(Response<RecentPhotoResponse> response) {
        if (!response.isSuccessful()) {
            Log.e("TAG", "Fail onResponse");
            return;
        }

        RecentPhotoResponse recentPhoto = response.body();
        if (recentPhoto.photos != null) {
            PhotosPageInfo photoList = recentPhoto.photos;
            if (photoList.photo != null) {
                for (Photo photo : photoList.photo) {
                    adapterRepository.addItem(0, photo);
                }
            }
        }

        getView().onRefresh();
    }

    private void failLoadImage() {
        Log.e("TAG", "onFailure");
    }

    public void photoItemClick(int position) {
        Photo photo = (Photo) adapterRepository.getItem(position);
        getView().onBottomSheetShow(photo);
    }
}
