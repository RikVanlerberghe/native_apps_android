package com.example.rik.myapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.rik.myapplication.R
import com.example.rik.myapplication.database.sqlite.DBHelper
import com.example.rik.myapplication.fragments.home.BalanceFragment
import com.example.rik.myapplication.fragments.home.BuyCardFragment
import com.example.rik.myapplication.fragments.home.BuyDrinkFragment
import com.example.rik.myapplication.fragments.home.HomeFragment
import com.example.rik.myapplication.fragments.inloggen.InlogFragment
import com.example.rik.myapplication.fragments.inloggen.RegistreerFragment
import com.example.rik.myapplication.fragments.settings.InfoFragment
import com.example.rik.myapplication.fragments.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

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
            "settings_settings" -> {
                fragment = SettingsFragment()
                currentFragmentTag = fragmentName
            }
            "settings_info" -> {
                fragment = InfoFragment()
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
            //navigation menu
            "home" -> {
                fragment = HomeFragment()
                currentFragmentTag = fragmentName
            }
            "balance" -> {
                fragment = BalanceFragment()
                currentFragmentTag = fragmentName
            }
            "game" -> {
                startActivity(Intent(this, GameActivity::class.java))
                return
            }
            "inloggen_registreren" -> {
                fragment = RegistreerFragment()
                currentFragmentTag = fragmentName
            }
            "inloggen_inloggen" -> {
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
                goTo("settings_settings")
            }
            R.id.action_info -> {
                goTo("settings_info")
            }
            R.id.action_logOff -> {
                val editor = getSharedPreferences("myPref", Context.MODE_PRIVATE)
                editor.edit().putString("user", "0")
                editor.edit().putString("username", "").apply()
                goTo("home")
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
                goTo("inloggen_registreren")
            }
            R.id.nav_inloggen -> {
                goTo("inloggen_inloggen")
            }
            R.id.nav_game -> {
                goTo("game")
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
