package com.fiap.giftgift.ui.main


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiap.giftgift.model.Gift
import com.fiap.giftgift.ui.List.GiftListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_gift_list.*
import kotlinx.android.synthetic.main.fragment_gift_list.view.*


class GiftListFragment : Fragment() {

    var giftlist:  ArrayList<Gift> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(com.fiap.giftgift.R.layout.fragment_gift_list, container, false)
        val recyclerView = rootView.rvGiftList
        recyclerView.adapter = GiftListAdapter(this.context!!, GetDatabase())
        recyclerView.layoutManager = LinearLayoutManager(this.context!!)

        return rootView

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun GetDatabase( ):List<Gift>   {

        var fbAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val user = db.collection("users")
        var userInfo:  MutableMap<String, Any> = mutableMapOf()
        var email: String

        if (fbAuth.currentUser != null) {

            val userlog = fbAuth.currentUser
            email = userlog!!.email!!

            if (email != null) {

                user.whereEqualTo("email", email).get().addOnSuccessListener { documents ->
                    for (document in documents) {

                        userInfo = document.data

                    }

                    if (userInfo["name"] != null) {
                        tvNameProfile.text = userInfo["name"] as String
                    }

                    var giftInit: ArrayList<String>

                    if (userInfo["gifts"] != null) {
                        giftInit = userInfo["gifts"] as ArrayList<String>
                        giftInit.forEach {
                            giftlist.add(Gift(it))
                        }
                    }

                    //Atualiza RV
                    rvGiftList.adapter?.notifyDataSetChanged()

                }.addOnFailureListener { exception ->
                    Toast.makeText(this.context!!, "erro " + exception, Toast.LENGTH_LONG).show()


                }


            }

        } else {
            Toast.makeText(this.context!!,
                    "Usuario não autenticado!",
                    Toast.LENGTH_LONG).show()

        }

        return giftlist

    }




}
