package com.leushinilya.loftcoin.di.components

import android.app.Application
import android.content.Context
import com.leushinilya.loftcoin.di.modules.AppModule
import com.leushinilya.loftcoin.di.modules.DataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AppModule::class, DataModule::class]
)
abstract class AppComponent {

    @Component.Builder
    abstract class Builder{
        @BindsInstance
        abstract fun application(app: Application): Builder
        abstract fun build(): AppComponent
    }

    abstract fun context(): Context

}