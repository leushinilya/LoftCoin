package com.leushinilya.loftcoin.utils

import dagger.Module
import dagger.Provides

//TODO: delete, useless
@Module
abstract class ViewModelModule {
//    @Binds
//    abstract fun viewModelFactory(loftViewModelFactory: LoftViewModelFactory): ViewModelProvider.Factory

    companion object {
        @Provides
        fun ratesViewModelFactory(): RatesViewModelFactory{
            return RatesViewModelFactory()
        }
    }

}