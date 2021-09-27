package com.leushinilya.loftcoin.data.wallets

import com.leushinilya.loftcoin.data.rates.Coin
import java.util.*

data class Transaction(val uid:String, val coin: Coin, val amount: Double, val createdAt: Date)
