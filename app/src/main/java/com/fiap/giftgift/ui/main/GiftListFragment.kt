package com.fiap.giftgift.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiap.giftgift.R
import com.fiap.giftgift.api.getGiftListAPI
import com.fiap.giftgift.model.Gift
import com.fiap.giftgift.model.GiftListResponse
import com.fiap.giftgift.ui.List.GiftListAdapter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_gift_list.*


class GiftListFragment : Fragment() {

    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_gift_list, container, false)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carregardados()
    }

    private fun exibeNaLista(gifts: List<Gift>){
        rvGiftList.adapter = GiftListAdapter(this.context!!, gifts,{
            Toast.makeText(this.context, it.nome, Toast.LENGTH_LONG).show()
        } )
        rvGiftList.layoutManager = LinearLayoutManager(this.context)
    }

    private fun exibeErro(erro: String){
        Toast.makeText(this.context!!, erro, Toast.LENGTH_LONG).show()
    }

    private fun carregardados(){
        getGiftListAPI()
                .buscar(150)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GiftListResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: GiftListResponse) {
                        exibeNaLista(t.gifts)
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
