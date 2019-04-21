package com.fiap.giftgift.ui.List

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.fiap.giftgift.R
import com.fiap.giftgift.ui.main.FriendsFragment
import com.fiap.giftgift.model.Friends
import kotlinx.android.synthetic.main.friend_row.view.*


class FriendsAdapter(private val list: List<Friends>)
    : RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FriendViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend: Friends = list[position]
        holder.bind(friend)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class FriendViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_friends, parent, false)) {
        private var mTitleView: TextView?  = null
        private var mImgView:   ImageView? = null



        init {
            mTitleView = itemView.findViewById(R.id.tvName)
            mImgView   = itemView.findViewById(R.id.ivFriend)

        }

        fun bind(friend: Friends) {
            mTitleView?.text = friend.name
            mImgView?.setImageResource(R.drawable.ic_card_giftcard_black_24dp)
        }

    }



}



