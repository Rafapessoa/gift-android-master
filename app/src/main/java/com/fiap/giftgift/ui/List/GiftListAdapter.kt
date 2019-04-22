package com.fiap.giftgift.ui.List

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.giftgift.R
import com.fiap.giftgift.api.getPicassoAuth
import com.fiap.giftgift.model.Gift
import kotlinx.android.synthetic.main.giftlist_row.view.*


class GiftListAdapter(
        private val context: Context,
        private val gifts: List<Gift>,
        private val listener: (Gift) -> Unit
) :
        RecyclerView.Adapter<GiftListAdapter.GiftListViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftListViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.giftlist_row,parent,false  )
        return GiftListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gifts.size
    }

    override fun onBindViewHolder(holder: GiftListViewHolder, position: Int) {
       holder.bindView(gifts[position], listener)
    }


    class GiftListViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){

        fun bindView(gift: Gift ,
                     listener: (Gift) -> Unit ) = with(itemView){
            tvGiftList.text = gift.nome
            getPicassoAuth(itemView.context)
                    .load("https://pokedexdx.herokuapp.com${gift.imagem}")
                    .into(ivGiftList)

            setOnClickListener{ listener(gift) }
        }

    }


}