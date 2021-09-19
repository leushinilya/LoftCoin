package com.leushinilya.loftcoin.data

import com.leushinilya.loftcoin.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class DataModule {

    companion object {

        @Provides
        @Singleton
        fun okHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                        val request = chain.request()
                        chain.proceed(request.newBuilder()
                                .addHeader(CmcAPI.API_KEY, BuildConfig.API_KEY)
                                .build())
                    }).build()
        }

        @Provides
        @Singleton
        fun retrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                    .baseUrl("https://pro-api.coinmarketcap.com")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        @Provides
        fun cmcApi(retrofit: Retrofit): CmcAPI {
            return retrofit.create(CmcAPI::class.java)
        }


    }

}