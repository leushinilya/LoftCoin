package com.leushinilya.loftcoin

import com.leushinilya.loftcoin.data.CmcAPI
import com.leushinilya.loftcoin.data.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [DataModule::class]
)
abstract class AppComponent {

    abstract fun cmcAPI(): CmcAPI

}