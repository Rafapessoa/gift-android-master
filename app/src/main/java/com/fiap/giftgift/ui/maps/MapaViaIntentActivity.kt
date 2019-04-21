package com.fiap.giftgift.ui.maps

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fiap.giftgift.R
import kotlinx.android.synthetic.main.activity_mapa_via_intent.*

class MapaViaIntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa_via_intent)


        btExibirRestaurantes.setOnClickListener {
            val query = "presentes"
            val geo = "geo:0,0?q=$query"
            val geoURI = Uri.parse(geo)
            val intent = Intent(Intent.ACTION_VIEW, geoURI)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }


    }
}
