package tech.thdev.butter_knife_example.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tech.thdev.butter_knife_example.BuildConfig;
import tech.thdev.butter_knife_example.network.domain.RecentPhotoResponse;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public interface PhotoServiceInterface {

    @GET("?format=json&nojsoncallback=1&method=flickr.interestingness.getList&api_key=" + BuildConfig.FLICKER_API_KEY)
    Call<RecentPhotoResponse> getFlickrPhotos(
            @Query("page") int page);

    @GET("?method=flickr.photos.search&safe_search=1&format=json&nojsoncallback=1&api_key=" + BuildConfig.FLICKER_API_KEY)
    Call<RecentPhotoResponse> getSearchPhotoResponse(
            @Query("per_page") int perPage,
            @Query("page") int page,
            @Query("text") String text);

    @GET("?method=flickr.photos.getRecent&format=json&nojsoncallback=1&api_key=" + BuildConfig.FLICKER_API_KEY)
    Call<RecentPhotoResponse> getRecentPhotoResponse(
            @Query("per_page") int perPage,
            @Query("page") int page);
}
