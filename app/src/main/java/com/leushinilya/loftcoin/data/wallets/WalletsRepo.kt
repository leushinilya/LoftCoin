package com.leushinilya.loftcoin.data.wallets

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.leushinilya.loftcoin.data.rates.db.CoinsRepoDB
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletsRepo @Inject constructor(val coinsRepoDB: CoinsRepoDB){

    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun wallets(currency: String): List<Wallet> {

        val wallets = Collections.emptyList<Wallet>()

        val registration: ListenerRegistration = firestore.collection("wallets")
            .addSnapshotListener { snapshots, error ->
                if (snapshots != null) {
                    for (doc in snapshots.documents) {
                        wallets.add(
                            Wallet(
                                doc.id,
                                coinsRepoDB.db.coins().fetchOne(doc.getLong("coidId") ?: 1),
                                ((doc.getDouble("balance") ?: 0) as Double)
                            )
                        )
                    }
                }
            }

        return wallets
    }

    fun transactions(wallet: Wallet): Observable<List<Transaction>> {
        return Observable.empty()
    }
}