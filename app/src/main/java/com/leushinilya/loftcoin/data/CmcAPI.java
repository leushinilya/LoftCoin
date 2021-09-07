package com.leushinilya.loftcoin.data;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CmcAPI {

    String API_KEY = "X-CMC_PRO_API_KEY";

    @GET("/v1/cryptocurrency/listings/latest")
    Single<Listings> listings(@Query("convert") String convert);
}
