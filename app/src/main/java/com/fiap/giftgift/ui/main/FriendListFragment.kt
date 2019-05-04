package com.fiap.giftgift.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.giftgift.model.Friend
import com.fiap.giftgift.ui.List.FriendListAdapter
import kotlinx.android.synthetic.main.fragment_friend_list.view.*
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_friend_list.*


class FriendListFragment : Fragment() {

    private val CADASTRO_REQUEST_CODE = 1
    var contactlist:  ArrayList<Friend> = ArrayList()
    var friendlist:  ArrayList<Friend> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(com.fiap.giftgift.R.layout.fragment_friend_list, container, false)
        val recyclerView = rootView.rvFriendtList
        recyclerView.adapter = FriendListAdapter(this.context!!, GetDatabase())
        recyclerView.layoutManager = LinearLayoutManager(this.context!!)

        return rootView

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun GetDatabase( ):List<Friend>   {

        var fbAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val user = db.collection("users")
        var userInfo:  MutableMap<String, Any> = mutableMapOf()
        var email: String

        friendlist.clear()

        if (fbAuth.currentUser != null) {

            val userlog = fbAuth.currentUser
            email = userlog!!.email!!

            if (email != null) {

                friendlist.clear()
                carregardados()
                contactlist.forEach {
                    email =  it.email
                    user.whereEqualTo("email", email).get().addOnSuccessListener { documents ->
                        for (document in documents) {
                            userInfo = document.data
                        }

                        if (userInfo["name"] != null) {
                            if (userInfo["email"] == email) {
                                friendlist.add(Friend(userInfo["name"] as String, userInfo["email"] as String))
                                if (friendlist.size > 0) {
                                    rvFriendtList.adapter?.notifyDataSetChanged()
                                }
                                email = ""
                            }
                        }

                    }.addOnFailureListener { exception ->
                        Toast.makeText(this.context!!, getString(com.fiap.giftgift.R.string.error) + exception, Toast.LENGTH_LONG).show()
                    }

                }

            }
        } else {
            Toast.makeText(this.context!!,
                    getString(com.fiap.giftgift.R.string.auth_not),
                    Toast.LENGTH_LONG).show()
        }

        return friendlist
    }


    private fun carregardados() {

        //TODOO Pegar lista de contatos do dispositivo

        contactlist.add(Friend("Caio"   ,"caio@caio.com"))
        contactlist.add(Friend("Diego"  ,"diego@diego.com"))
        contactlist.add(Friend("Douglas","douglas@douglas.com"))
        contactlist.add(Friend("Julio"  ,"julio@julio.com"))
        contactlist.add(Friend("Rafael" ,"rafael@rafael.com"))

    }

}
