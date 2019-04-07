package com.fiap.giftgift.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import com.fiap.giftgift.R
import com.fiap.giftgift.ui.chamadas.ChamadasFragment
import com.fiap.giftgift.ui.conversas.ConversasFragment
import com.fiap.giftgift.ui.status.StatusFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var contadorMensagensNaoLidas = intArrayOf(0, 2, 0)

    lateinit var adapter: MainViewPagerAdapter

    val fragments = listOf(ConversasFragment(), StatusFragment(), ChamadasFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        setupViewPager()
        setupTabIcons()
    }

    private fun setupViewPager() {
        viewpager.offscreenPageLimit = fragments.size

        adapter = MainViewPagerAdapter(supportFragmentManager)

        addFragments()

        viewpager.adapter = adapter

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                viewpager.setCurrentItem(position, false)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun addFragments() {
        for (fragment in fragments) {
            adapter.addFragment(fragment)
        }
    }

    private fun setupTabIcons() {
        tablayout.setupWithViewPager(viewpager)
        for (i in fragments.indices) {
            tablayout.getTabAt(i)?.customView = configuraTabView(
                    getString(fragments[i].getTitulo()),
                    contadorMensagensNaoLidas[i]
            )
        }
    }

    private fun configuraTabView(titulo: String, contador: Int): View {
        val view = layoutInflater.inflate(R.layout.custom_tab, null)
        val tvTitle = view.findViewById(R.id.tvTitle) as TextView
        val tvCount = view.findViewById(R.id.tvCount) as TextView

        tvTitle.text = titulo

        if (contador > 0) {
            tvCount.visibility = View.VISIBLE
            tvCount.text = "${contador}"
        } else
            tvCount.visibility = View.GONE

        return view
    }
}
