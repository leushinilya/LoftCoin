package com.leushinilya.loftcoin

import android.app.Application
import android.content.Context
import com.leushinilya.loftcoin.data.db.CoinsRepoDB
import com.leushinilya.loftcoin.data.remote.CmcAPI
import com.leushinilya.loftcoin.data.DataModule
import com.leushinilya.loftcoin.data.db.LoftDatabase
import com.leushinilya.loftcoin.ui.main.rates.RatesViewModel
import com.leushinilya.loftcoin.utils.RatesViewModelFactory
import com.leushinilya.loftcoin.utils.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AppModule::class, DataModule::class, ViewModelModule::class]
)
abstract class AppComponent {

    @Component.Builder
    abstract class Builder{
        @BindsInstance
        abstract fun application(app: Application): Builder
        abstract fun build(): AppComponent
    }

    abstract fun context(): Context
    abstract fun cmcAPI(): CmcAPI
    abstract fun database(): LoftDatabase
    abstract fun coinsRepo(): CoinsRepoDB
    abstract fun inject(ratesViewModel: RatesViewModel)


//    TODO: delete, useless
    abstract fun ratesViewModelFactory(): RatesViewModelFactory
    abstract fun inject(ratesViewModelFactory: RatesViewModelFactory)

}