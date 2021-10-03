package com.leushinilya.loftcoin

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
abstract class AppModule {

    companion object {

        @Provides
        @Singleton
        fun context(app: Application): Context {
            return app.applicationContext
        }

        @Provides
        @Singleton
        fun ioExecutor(): ExecutorService{
            val poolSize = Runtime.getRuntime().availableProcessors()
            return Executors.newFixedThreadPool(poolSize * 2 + 1)
        }

    }

}