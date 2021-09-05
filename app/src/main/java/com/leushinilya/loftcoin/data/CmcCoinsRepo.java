package com.leushinilya.loftcoin.data;

import androidx.annotation.NonNull;

import com.leushinilya.loftcoin.BuildConfig;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class CmcCoinsRepo implements CoinsRepo{

    private final CmcAPI api;

    public CmcCoinsRepo(){
        api = createRetrofit(createOkHttpClient()).create(CmcAPI.class);
    }

    @Override
    public List<Coin> listings(String currency) throws IOException {
        final Response<Listings> response = api.listings(currency).execute();
        if(response.isSuccessful()){
            final Listings listings = response.body();
            if(listings!=null){
                return listings.data;
            }
        } else {
            final ResponseBody responseBody = response.errorBody();
            if(responseBody!=null){
                throw new IOException(responseBody.string());
            }
        }
        return Collections.emptyList();
    }

    private OkHttpClient createOkHttpClient(){
        OkHttpClient.Builder builder=  new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    final Request request = chain.request();
                    return chain.proceed(request.newBuilder()
                            .addHeader(CmcAPI.API_KEY, BuildConfig.API_KEY)
                            .build());
                });

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            interceptor.redactHeader(CmcAPI.API_KEY);
            builder.addInterceptor(interceptor);
        }

        return builder.build();
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder()
                        .add(Coin.class)
                        .add(Listings.class)
                        .build()))
                .build();
    }

}
