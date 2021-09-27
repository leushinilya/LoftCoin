package com.leushinilya.loftcoin.data.wallets

import com.leushinilya.loftcoin.data.rates.Coin

data class Wallet (val uid: String, val coin: Coin, val balance: Double){
}