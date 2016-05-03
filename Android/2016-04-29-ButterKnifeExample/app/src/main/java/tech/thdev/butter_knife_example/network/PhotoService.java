package tech.thdev.butter_knife_example.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tech.thdev.butter_knife_example.BuildConfig;
import tech.thdev.butter_knife_example.network.domain.RecentPhoto;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public interface PhotoService {

    @GET("?format=json&nojsoncallback=1&method=flickr.interestingness.getList&api_key=" + BuildConfig.FLICKER_API_KEY)
    Call<RecentPhoto> getFlickrPhotos(@Query("page") int page);
}
