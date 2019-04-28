package com.fiap.giftgift.ui.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.fiap.giftgift.R
import com.fiap.giftgift.api.getFriendListAPI
import com.fiap.giftgift.api.getGiftListAPI
import com.fiap.giftgift.model.Friend
import com.fiap.giftgift.model.FriendListResponse
import com.fiap.giftgift.model.Gift
import com.fiap.giftgift.model.GiftListResponse
import com.fiap.giftgift.ui.List.FriendListAdapter
import com.fiap.giftgift.ui.List.GiftListAdapter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_friend_list.*
import kotlinx.android.synthetic.main.fragment_gift_list.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FriendListFragment : Fragment() {

    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_friend_list, container, false)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carregardados()
    }

    private fun exibeNaLista(friends: List<Friend>){
        rvFriendtList.adapter = FriendListAdapter(this.context!!, friends,{
            Toast.makeText(this.context, it.nome, Toast.LENGTH_LONG).show()
        } )
        rvFriendtList.layoutManager = LinearLayoutManager(this.context)
    }

    private fun exibeErro(erro: String){
        Toast.makeText(this.context!!, erro, Toast.LENGTH_LONG).show()
    }

    private fun carregardados(){
        getFriendListAPI()
                .buscar(150)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<FriendListResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: FriendListResponse) {
                        exibeNaLista(t.friends)
                    }

                    override fun onError(e: Throwable) {
                        exibeErro(e.message!!)
                    }
                } )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}
