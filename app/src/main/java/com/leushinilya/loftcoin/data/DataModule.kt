package com.leushinilya.loftcoin.data

import android.content.Context
import androidx.room.Room
import com.leushinilya.loftcoin.BuildConfig
import com.leushinilya.loftcoin.data.db.CoinsRepoDB

import com.leushinilya.loftcoin.data.db.LoftDatabase
import com.leushinilya.loftcoin.data.remote.CmcAPI
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ExecutorService
import javax.inject.Singleton

@Module
abstract class DataModule {

    companion object {

        @Provides
        @Singleton
        fun okHttpClient(executor: ExecutorService): OkHttpClient {
            return OkHttpClient.Builder()
                    .dispatcher(Dispatcher(executor))
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
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        @Provides
        fun cmcApi(retrofit: Retrofit): CmcAPI {
            return retrofit.create(CmcAPI::class.java)
        }

        @Singleton
        @Provides
        fun database(context: Context): LoftDatabase{
            return if(BuildConfig.DEBUG){
                Room.inMemoryDatabaseBuilder(context, LoftDatabase::class.java).build()
            } else{
                Room.databaseBuilder(context, LoftDatabase::class.java, "loft.db").build()
            }
        }

        @Singleton
        @Provides
        fun coinsRepo(cmcAPI: CmcAPI, db: LoftDatabase, executor: ExecutorService): CoinsRepoDB {
            return CoinsRepoDB(
                cmcAPI,
                db,
                executor
            )
        }

    }

}