package com.st18apps.testvrg.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("mostpopular/v2/emailed/{period}.json")
    Observable<NewsResponse> getMostEmailed(@Path("period") int period,
                                            @Query("api-key") String key);

    @GET("mostpopular/v2/shared/{period}.json")
    Observable<NewsResponse> getMostShared(@Path("period") int period,
                                           @Query("api-key") String key);

    @GET("mostpopular/v2/viewed/{period}.json")
    Observable<NewsResponse> getMostViewed(@Path("period") int period,
                                           @Query("api-key") String key);

}
