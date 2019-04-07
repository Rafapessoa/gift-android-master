package com.fiap.giftgift.ui.conversas


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.giftgift.R
import com.fiap.giftgift.ui.base.BaseFragment


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ConversasFragment : BaseFragment() {

    override fun getTitulo(): Int {
        return R.string.tab_conversa
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversas, container, false)
    }


}
