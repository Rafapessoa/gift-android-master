package com.fiap.giftgift.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.giftgift.R
import com.fiap.giftgift.model.Friends
import com.fiap.giftgift.ui.List.FriendsAdapter
import kotlinx.android.synthetic.main.fragment_friends.*



class FriendsFragment : Fragment() {

    lateinit var fList : List<Friends>


    companion object {

        fun newInstance(): FriendsFragment = FriendsFragment()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carregardados()
        retainInstance = true

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_friends, container, false)


    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView node initialized here
        rvFriends.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity) //ConstraintLayout
            // set the custom adapter to the RecyclerView
            adapter = FriendsAdapter(fList)
        }
    }

    private fun carregardados(){

        fList = listOf(
                Friends("Adérito Tibiriçá", "atibirica@email.com", "1111-1111"),
                Friends("Cleiton Siqueira ", "csiqueira@email.com", "2222-2222"),
                Friends("Carlos Proença", "cproenca@email.com", "3333-3333"),
                Friends("Filipe Valadão", "fvaladao@email.om", "4444-4444"),
                Friends("Flávio Noite", "fnoite@email.com", "5555-5555"),
                Friends("Gisela Siebra", "gsiebra@email.com", "6666-6666"),
                Friends("Marco Lousã", "mlousa@email.com", "7777-7777"),
                Friends("Miriam Tabosa", "mtabosa@email.com", "8888-8888"),
                Friends("Rufus Ramírez", "rramirez@email.com", "9999-9999"),
                Friends("Socorro Cabreira", "scabreira@email.com", "1010-1010")
        )


    }




}
