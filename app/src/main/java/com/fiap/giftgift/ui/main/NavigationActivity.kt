package com.fiap.giftgift.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.FrameLayout
import com.fiap.giftgift.R
import com.fiap.giftgift.ui.maps.MapaViaIntentActivity
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.navigation_friends -> {

                    val fragment = FriendsFragment.Companion.newInstance()
                    addFragment(fragment)

                    return true
                }
                R.id.navigation_mylist -> {
                    val fragment = GiftListFragment()//MyListFragment()
                    addFragment(fragment)
                    return true
                }
                R.id.navigation_about -> {
                    var fragment = AboutFragment()
                    addFragment(fragment)
                    return true
                }
            }
            return false
        }

    }

    /**
     * add/replace fragment in container [framelayout]
     */
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        content = findViewById(R.id.content) as FrameLayout
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        val fragment = FriendsFragment.Companion.newInstance()
        addFragment(fragment)

        fbtMaps.setOnClickListener {
            val telaSeguinte = Intent(this, MapaViaIntentActivity::class.java)

            startActivityForResult(telaSeguinte,1)
        }

    }



}


