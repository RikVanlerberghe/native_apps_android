package com.example.rik.myapplication.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.rik.myapplication.R
import com.example.rik.myapplication.database.sqlite.DBHelper
import com.example.rik.myapplication.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.inloggen.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var currentFragmentTag: String = ""
    var db : DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)
        //this.deleteDatabase("database")

        setSupportActionBar(toolbar)
        goTo("home")

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    fun goTo(fragmentName: String){
        val addToBackStack = true
        var fragment = Fragment()

        when (fragmentName){
            "settings" -> {
                fragment = SettingsFragment()
                currentFragmentTag = fragmentName
            }
            "info" -> {
                fragment = InfoFragment()
                currentFragmentTag = fragmentName
            }
            "home" -> {
                fragment = HomeFragment()
                currentFragmentTag = fragmentName
            }
            "buyCard" -> {
                fragment = BuyCardFragment()
                currentFragmentTag = fragmentName
            }
            "buyDrink" -> {
                fragment = BuyDrinkFragment()
                currentFragmentTag = fragmentName
            }
            "balance" -> {
                fragment = BalanceFragment()
                currentFragmentTag = fragmentName
            }
            "registreren" -> {
                fragment = InlogFragment()
                currentFragmentTag = fragmentName
            }
        }
        switchFragment(addToBackStack, fragmentName, fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                goTo("settings")
            }
            R.id.action_info -> {
                goTo("info")
            }
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                goTo("home")
            }
            R.id.nav_balance -> {
                goTo("balance")
            }
            R.id.nav_registreren -> {
                goTo("registreren")
            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun switchFragment(addToBackStack: Boolean, fragmentTag: String, fragment: Fragment) {
        val trans = supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment, fragmentTag)
        if (addToBackStack) trans.addToBackStack(fragmentTag)
        trans.commit()
    }
}
