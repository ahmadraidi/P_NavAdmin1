package com.example.p_navadmin.Admin

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.p_navadmin.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.admin_home.*
import kotlinx.android.synthetic.main.admin_home.drawerLayout
import kotlinx.android.synthetic.main.admin_home.nav_view
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.public_main.*

class Admin_Home: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var storeDetailsFragment: StoreDetailsFragment
    lateinit var adminVerifyListFragment: AdminVerifyListFragment
    lateinit var userFeedbackFragment: UserFeedbackFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_home)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar?.title = "Store Details"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle (
            this,
            drawerLayout,
            toolbar,
            (R.string.open),
            (R.string.close)
        ) {

        }

        drawerToggle.isDrawerIndicatorEnabled =true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        // now implement navigation item selected listener
        // the default fragment is StoreDetailsFragement
        storeDetailsFragment =
            StoreDetailsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, storeDetailsFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        // so this is our fragment code

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        fun burgerbar(tajuk: String) {

            setSupportActionBar(toolbar)
            val actionBar = supportActionBar
            actionBar?.title = tajuk

            val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle (
                this,
                drawerLayout,
                toolbar,
                (R.string.open),
                (R.string.close)
            ) {

            }

        }
        // now create our related fragment
        when (menuItem.itemId) {
            R.id.store_details ->  {
                burgerbar("Store Details")
                storeDetailsFragment =
                    StoreDetailsFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, storeDetailsFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.verify_list -> {
                burgerbar("Verify List")
                adminVerifyListFragment =
                    AdminVerifyListFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, adminVerifyListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.user_feedback -> {
                burgerbar("User Feedback")
                userFeedbackFragment =
                    UserFeedbackFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, userFeedbackFragment)
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