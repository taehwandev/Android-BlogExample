package tech.thdev.butter_knife_example.presenter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.thdev.butter_knife_example.adapter.model.PhotoDataModel;
import tech.thdev.butter_knife_example.base.presenter.BasePresenter;
import tech.thdev.butter_knife_example.network.RetrofitPhoto;
import tech.thdev.butter_knife_example.network.domain.Photo;
import tech.thdev.butter_knife_example.network.domain.Photos;
import tech.thdev.butter_knife_example.network.domain.RecentPhoto;
import tech.thdev.butter_knife_example.presenter.view.PhotoPresenterView;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoPresenter extends BasePresenter<PhotoPresenterView> {

    private PhotoDataModel photoDataModel;
    private RetrofitPhoto retrofitPhoto;

    public PhotoPresenter(PhotoPresenterView view, RetrofitPhoto retrofitPhoto, PhotoDataModel photoDataModel) {
        super(view);

        this.retrofitPhoto = retrofitPhoto;
        this.photoDataModel = photoDataModel;
    }

    public void loadPhotos(final int page) {
        Call<RecentPhoto> photos = retrofitPhoto.getRecentPhoto(page);
        photos.enqueue(new Callback<RecentPhoto>() {
            @Override
            public void onResponse(Call<RecentPhoto> call, Response<RecentPhoto> response) {
                if (!response.isSuccessful()) {
                    Log.e("TAG", "Fail onResponse");
                    return;
                }

                RecentPhoto recentPhoto = response.body();
                if (recentPhoto.photos != null) {
                    Photos photoList = recentPhoto.photos;
                    if (photoList.photo != null) {
                        for (Photo photo : photoList.photo) {
                            photoDataModel.add(photo, false);
                        }
                    }
                }

                getView().onRefresh();
            }

            @Override
            public void onFailure(Call<RecentPhoto> call, Throwable t) {
                Log.e("TAG", "onFailure");
            }
        });
    }

    public void photoItemClick(int position) {
        Photo photo = photoDataModel.getPhotoItem(position);
        getView().onBottomSheetShow(photo);
    }
}
