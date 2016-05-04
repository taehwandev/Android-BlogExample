package tech.thdev.butter_knife_example.presenter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.thdev.butter_knife_example.adapter.model.PhotoDataModel;
import tech.thdev.butter_knife_example.base.presenter.BasePresenter;
import tech.thdev.butter_knife_example.network.RetrofitPhoto;
import tech.thdev.butter_knife_example.network.domain.Photo;
import tech.thdev.butter_knife_example.network.domain.PhotosPageInfo;
import tech.thdev.butter_knife_example.network.domain.RecentPhotoResponse;
import tech.thdev.butter_knife_example.presenter.view.PhotoPresenterView;
import tech.thdev.butter_knife_example.presenter.view.PhotoSearchPresenterView;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoSearchPresenter extends BasePresenter<PhotoSearchPresenterView> {

    private PhotoDataModel photoDataModel;
    private RetrofitPhoto retrofitPhoto;

    public PhotoSearchPresenter(PhotoSearchPresenterView view, RetrofitPhoto retrofitPhoto, PhotoDataModel photoDataModel) {
        super(view);

        this.retrofitPhoto = retrofitPhoto;
        this.photoDataModel = photoDataModel;
    }

    public void loadSearchPhotos(int perPage, int page, String text) {
        Call<RecentPhotoResponse> photos = retrofitPhoto.getSearchPhoto(perPage, page, text);
        photos.enqueue(new Callback<RecentPhotoResponse>() {
            @Override
            public void onResponse(Call<RecentPhotoResponse> call, Response<RecentPhotoResponse> response) {
                loadImage(response);
            }

            @Override
            public void onFailure(Call<RecentPhotoResponse> call, Throwable t) {
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
                    photoDataModel.add(photo, false);
                }
            }
        }

        getView().onRefresh();
    }

    private void failLoadImage() {
        Log.e("TAG", "onFailure");
    }

    public void photoItemClick(int position) {
        Photo photo = photoDataModel.getPhotoItem(position);
        getView().onBottomSheetShow(photo);
    }
}
