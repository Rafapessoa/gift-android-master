package com.fiap.giftgift.ui.List

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiap.giftgift.model.Gift
import com.fiap.giftgift.ui.main.NavigationActivity
import com.fiap.giftgift.ui.maps.MapaViaIntentActivity
import kotlinx.android.synthetic.main.giftlist_row.view.*


class GiftListAdapter(
        private val context: Context,
        private var gifts: List<Gift>
) :RecyclerView.Adapter<GiftListAdapter.GiftListViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftListViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(com.fiap.giftgift.R.layout.giftlist_row,parent,false  )

        return GiftListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gifts.size
    }

    override fun onBindViewHolder(holder: GiftListViewHolder, position: Int) {
        val gift = gifts[position]
        val btMenu = holder.btMenuOptions
        holder.name.text = gift.name

        //***********
        btMenu.setOnClickListener(View.OnClickListener {
            Toast.makeText(this.context!!,"Teste Menu de opcoes", Toast.LENGTH_LONG).show()

                //val telaSeguinte = Intent(this.context, MapaViaIntentActivity::class.java)

               //startActivityForResult(telaSeguinte,1)
            })
        //***********

    }


    class GiftListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.tvGiftList
        val btMenuOptions = itemView.btMenuOptions

        fun bindView(gift: Gift) {
            name.text = gift.name
        }
    }

}