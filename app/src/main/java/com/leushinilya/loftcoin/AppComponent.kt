package com.leushinilya.loftcoin

import android.app.Application
import android.content.Context
import com.leushinilya.loftcoin.data.rates.db.CoinsRepoDB
import com.leushinilya.loftcoin.data.rates.remote.CmcAPI
import com.leushinilya.loftcoin.data.DataModule
import com.leushinilya.loftcoin.data.rates.db.LoftDatabase
import com.leushinilya.loftcoin.data.wallets.WalletsRepo
import com.leushinilya.loftcoin.ui.main.rates.RatesViewModel
import com.leushinilya.loftcoin.ui.main.wallets.WalletsViewModel
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

//    provide
    abstract fun context(): Context
    abstract fun cmcAPI(): CmcAPI
    abstract fun database(): LoftDatabase
    abstract fun coinsRepoDB(): CoinsRepoDB
    abstract fun walletsRepo(): WalletsRepo

//    inject
    abstract fun inject(ratesViewModel: RatesViewModel)
    abstract fun inject(walletsViewModel: WalletsViewModel)

}