package com.fiap.giftgift.ui.main

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fiap.giftgift.R
import com.fiap.giftgift.model.Gift
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_gift_upsert.*



class GiftUpsertActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val user = db.collection("users")
    var userInfo:  MutableMap<String, Any> = mutableMapOf()
    var giftInit: ArrayList<String> = ArrayList()
    var email: String = ""
    var giftlist:  ArrayList<Gift> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.fiap.giftgift.R.layout.activity_gift_upsert)

        btAddGift.setOnClickListener {
            saveDatabase()
        }

    }



    private fun saveDatabase() {



        if (fbAuth.currentUser != null) {

            val userlog = fbAuth.currentUser

            if (userlog != null) {
                email = userlog.email!!
            }

        } else {
            Toast.makeText(this@GiftUpsertActivity,
                    "Usuario não autenticado!",
                    Toast.LENGTH_LONG).show()
        }

        if (!email.isEmpty()){



            user.whereEqualTo("email", email).get().addOnSuccessListener { documents ->
                    for (document in documents) {

                            userInfo = document.data
                            Toast.makeText(this@GiftUpsertActivity, "" + userInfo, Toast.LENGTH_LONG).show()
                            upsert()
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this@GiftUpsertActivity, "erro " + exception, Toast.LENGTH_LONG).show()


            }.addOnFailureListener { e ->
                Toast.makeText(this@GiftUpsertActivity,
                        "Error read Gift list !",
                        Toast.LENGTH_LONG).show()
            }


        }

    }

    private fun upsert(){


        if (userInfo["gifts"] != null) {
            giftInit = userInfo["gifts"] as ArrayList<String>
            giftInit.forEach {
                giftlist.add(Gift(it))
            }
        }

        ///tudo ok
        giftInit.add(etGiftName.text.toString())
        userInfo["gifts"] = giftInit


        user.document(email).update(userInfo).addOnSuccessListener {

            Toast.makeText(this@GiftUpsertActivity, "Gift criado com sucesso!", Toast.LENGTH_LONG).show()
            val intent = Intent()

            setResult(Activity.RESULT_OK, intent)
            finish()

        }.addOnFailureListener { e ->
            Toast.makeText(this@GiftUpsertActivity,
                    "Error adding Gift" + e + "Error!! Fail to create / insert a new Gift !",
                    Toast.LENGTH_LONG).show()
        }

    }





}
