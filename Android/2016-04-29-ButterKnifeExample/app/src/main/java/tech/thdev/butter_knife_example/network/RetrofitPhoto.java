package tech.thdev.butter_knife_example.network;

import retrofit2.Call;
import retrofit2.Retrofit;
import tech.thdev.butter_knife_example.network.domain.RecentPhoto;

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

    public Call<RecentPhoto> getRecentPhoto(int page) {
        return retrofit.create(PhotoService.class).getFlickrPhotos(page);
    }
}
