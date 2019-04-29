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



class FriendListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(com.fiap.giftgift.R.layout.fragment_friend_list, container, false)
        val recyclerView = rootView.rvFriendtList
        recyclerView.adapter = FriendListAdapter(this.context!!, carregardados())
        recyclerView.layoutManager = LinearLayoutManager(this.context!!)

        return rootView

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun carregardados(): List<Friend> {
        return listOf(
                Friend("Caio"),
                Friend("Diego"),
                Friend("Douglas"),
                Friend("Julio"),
                Friend("Rafael"))
    }

}
