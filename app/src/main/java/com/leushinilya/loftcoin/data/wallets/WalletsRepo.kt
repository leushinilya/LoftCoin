package com.leushinilya.loftcoin.data.wallets

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.leushinilya.loftcoin.data.rates.db.CoinsRepoDB
import io.reactivex.Observable
import java.util.concurrent.ExecutorService
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.system.exitProcess

@Singleton
class WalletsRepo @Inject constructor(val coinsRepoDB: CoinsRepoDB, val executor: ExecutorService) {

    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun wallets(wallets: MutableLiveData<ArrayList<Wallet>>) {

        val registration: ListenerRegistration = firestore.collection("wallets")
            .addSnapshotListener { snapshots, error ->
                executor.submit {
                    Log.d("wwm", "start")
                    val walletsList = ArrayList<Wallet>()
                    if (snapshots != null) {
                        for (doc in snapshots.documents) {
                            walletsList.add(
                                Wallet(
                                    doc.id,
                                    coinsRepoDB.db.coins().fetchOne(doc.getLong("coinid") ?: 0),
                                    ((doc.getDouble("balance") ?: 0) as Double)
                                )
                            )
                        }
                    }
                    wallets.postValue(walletsList)
                }
            }
    }


    fun transactions(wallet: Wallet): Observable<List<Transaction>> {
        return Observable.empty()
    }
}