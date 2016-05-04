package tech.thdev.butter_knife_example.network;

import retrofit2.Call;
import retrofit2.Retrofit;
import tech.thdev.butter_knife_example.network.domain.RecentPhotoResponse;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class RetrofitPhoto {

    private Retrofit retrofit;

    private static RetrofitPhoto retrofitPhoto;

    public static RetrofitPhoto getRetrofitPhoto() {
        if (retrofitPhoto == null) {
            synchronized (RetrofitPhoto.class) {
                if (retrofitPhoto == null) {
                    retrofitPhoto = new RetrofitPhoto();
                }
            }
        }
        return retrofitPhoto;
    }

    private RetrofitPhoto() {
        retrofit = RetrofitCreator.createRetrofit();
    }

    public Call<RecentPhotoResponse> getRecentPhoto(int page) {
        return retrofit.create(PhotoServiceInterface.class).getRecentPhotoResponse(20, page);
    }

    public Call<RecentPhotoResponse> getSearchPhoto(int perPage, int page, String text) {
        return retrofit.create(PhotoServiceInterface.class).getSearchPhotoResponse(perPage, page, text);
    }
}
