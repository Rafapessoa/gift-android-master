package com.fiap.giftgift.fcm

import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIdService: FirebaseInstanceIdService (){

    val TAG = "PushNotifService"
    lateinit var name: String

    override fun onTokenRefresh(){
        super.onTokenRefresh()
        val token = FirebaseInstanceId.getInstance().token
        FirebaseApp.initializeApp(this)
        Log.i("Token",FirebaseInstanceId.getInstance().token)
    }

}


