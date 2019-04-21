package com.fiap.giftgift

import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIdService: FirebaseInstanceIdService (){

    override fun onTokenRefresh(){
        super.onTokenRefresh()
        FirebaseApp.initializeApp(this)
        Log.i("Token",FirebaseInstanceId.getInstance().token)
    }

}

