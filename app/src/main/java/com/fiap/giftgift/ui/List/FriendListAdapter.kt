package com.fiap.giftgift.ui.List

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiap.giftgift.R
import com.fiap.giftgift.model.Friend
import kotlinx.android.synthetic.main.friendlist_row.view.*

class FriendListAdapter(
        private val context: Context,
        private val friends: List<Friend>
) :RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendListViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.friendlist_row,parent,false  )
        return FriendListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onBindViewHolder(holder: FriendListViewHolder, position: Int) {
        val friend = friends[position]
        holder.name.text = friend.name
        holder.bindView(friend)
    }

    class FriendListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.tvFriendList

        fun bindView(friend: Friend) {
            name.text = friend.name
            itemView.setOnClickListener {Toast.makeText(itemView.context!!,"Teste Menu de opcoes", Toast.LENGTH_LONG).show() }
        }
    }


}