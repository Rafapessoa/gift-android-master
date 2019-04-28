package com.fiap.giftgift.ui.List

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.giftgift.R
import com.fiap.giftgift.api.getPicassoAuth
import com.fiap.giftgift.model.Friend
import kotlinx.android.synthetic.main.friendlist_row.view.*


class FriendListAdapter(
        private val context: Context,
        private val friends: List<Friend>,
        private val listener: (Friend) -> Unit
) :
        RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendListViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.friendlist_row,parent,false  )
        return FriendListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onBindViewHolder(holder: FriendListViewHolder, position: Int) {
       holder.bindView(friends[position], listener)
    }


    class FriendListViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){

        fun bindView(friend: Friend ,
                     listener: (Friend) -> Unit ) = with(itemView){
            tvFriendList.text = friend.nome
            getPicassoAuth(itemView.context)
                    .load("https://pokedexdx.herokuapp.com${friend.imagem}")
                    .into(ivFriendList)

            setOnClickListener{ listener(friend) }
        }

    }


}