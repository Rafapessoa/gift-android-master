package com.fiap.giftgift.ui.List

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.giftgift.R
import com.fiap.giftgift.model.Gift
import kotlinx.android.synthetic.main.giftlist_row.view.*


class GiftListAdapter(
        private val context: Context,
        private val gifts: List<Gift>
) :RecyclerView.Adapter<GiftListAdapter.GiftListViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftListViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.giftlist_row,parent,false  )
        return GiftListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gifts.size
    }

    override fun onBindViewHolder(holder: GiftListViewHolder, position: Int) {
        val gift = gifts[position]
        holder.name.text = gift.name
    }


    class GiftListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.tvGiftList

        fun bindView(gift: Gift) {
            name.text = gift.name
        }

    }


}