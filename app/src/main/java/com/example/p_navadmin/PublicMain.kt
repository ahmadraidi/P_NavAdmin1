package com.example.p_navadmin

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.p_navadmin.Public.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.public_main.*

class PublicMain : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var indoorNavigationFragment: IndoorNavigationFragment
    lateinit var storeDirectoryFragment: StoreDirectoryFragment
    lateinit var promotionFragment: PromotionFragment
    lateinit var feedbackFragment: FeedbackFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.public_main)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Indoor Navigation"

        val drawerToggle: ActionBarDrawerToggle = object  : ActionBarDrawerToggle (
            this,
            drawerLayout,
            toolbar,
            (R.string.open),
            (R.string.close)
        ) {

        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //default fragment
        indoorNavigationFragment = IndoorNavigationFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, indoorNavigationFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        //
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        fun burgerbar(tajuk: String) {

            setSupportActionBar(toolbar)
            val actionBar = supportActionBar
            actionBar?.title = tajuk

            val drawerToggle: ActionBarDrawerToggle = object  : ActionBarDrawerToggle (
                this,
                drawerLayout,
                toolbar,
                (R.string.open),
                (R.string.close)
            ) {

            }

        }
        // now create fragment
        when (menuItem.itemId) {

            R.id.promotion -> {
                burgerbar("Promotion and Event")
                promotionFragment = PromotionFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, promotionFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.feedback ->  {
                burgerbar("Feedback")
                feedbackFragment = FeedbackFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, feedbackFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.indoorNavigation -> {
                burgerbar("Indoor Navigation")
                indoorNavigationFragment = IndoorNavigationFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, indoorNavigationFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.storeDirectory -> {
                burgerbar("Store Directory list")
                storeDirectoryFragment = StoreDirectoryFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, storeDirectoryFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }

}