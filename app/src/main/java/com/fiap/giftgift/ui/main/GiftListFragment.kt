package com.fiap.giftgift.ui.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.giftgift.model.Gift
import com.fiap.giftgift.ui.List.GiftListAdapter
import kotlinx.android.synthetic.main.fragment_gift_list.view.*


class GiftListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(com.fiap.giftgift.R.layout.fragment_gift_list, container, false)
        val recyclerView = rootView.rvGiftList
        recyclerView.adapter = GiftListAdapter(this.context!!, carregardados())
        recyclerView.layoutManager = LinearLayoutManager(this.context!!)

        return rootView

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun carregardados(): List<Gift> {
        return listOf(
                Gift("PS4"),
                Gift("Iphone"),
                Gift("Perfume"))
    }

}
