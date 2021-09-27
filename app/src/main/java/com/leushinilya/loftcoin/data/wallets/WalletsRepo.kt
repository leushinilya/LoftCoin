package com.leushinilya.loftcoin.data.wallets

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
class WalletsRepo {

    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun wallets(currency: String): Observable<List<Wallet>> {

//        Observable.create<QuerySnapshot> { emitter ->
//            val registration: ListenerRegistration = firestore.collection("wallets")
//                .addSnapshotListener { snapshots, error ->
//                    if (snapshots != null) emitter.onNext(snapshots)
//                    else if (error != null) emitter.tryOnError(error)
//                }
//            emitter.setCancellable(registration::remove)
//        }
//            .map({snapshots-> snapshots.documents })
//            .switchMapSingle {  }
        return Observable.empty()
    }

    fun transactions(wallet: Wallet): Observable<List<Transaction>> {
        return Observable.empty()
    }
}