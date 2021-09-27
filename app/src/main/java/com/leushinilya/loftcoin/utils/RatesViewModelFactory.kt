package com.leushinilya.loftcoin.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leushinilya.loftcoin.data.rates.remote.CmcAPI
import com.leushinilya.loftcoin.ui.main.rates.RatesViewModel
import javax.inject.Inject

//TODO: delete, useless
class RatesViewModelFactory: ViewModelProvider.Factory {

    @Inject
    lateinit var cmcAPI: CmcAPI

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RatesViewModel() as T
    }
}