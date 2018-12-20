package com.example.rik.myapplication.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import com.example.rik.myapplication.R
import com.example.rik.myapplication.fragments.game.CreateGameFragment
import com.example.rik.myapplication.fragments.game.GameFragment
import com.example.rik.myapplication.fragments.settings.InfoFragment
import com.example.rik.myapplication.fragments.settings.SettingsFragment

import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_game.*
import kotlinx.android.synthetic.main.app_bar_main.*

class GameActivity : AppCompatActivity() {

    private var currentFragmentTag: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        setSupportActionBar(toolbar_game)
        goTo("create_game")

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }
    fun goTo(fragmentName: String){
        val addToBackStack = true
        var fragment = Fragment()

        when (fragmentName){
            //settings
            "settings_settings" -> {
                fragment = SettingsFragment()
                currentFragmentTag = fragmentName
            }
            "settings_info" -> {
                fragment = InfoFragment()
                currentFragmentTag = fragmentName
            }
            //game fragments
            "create_game" -> {
                fragment = CreateGameFragment()
                currentFragmentTag = fragmentName
            }
            "play_game" -> {
                fragment = GameFragment()
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
        }
        return true
    }

    private fun switchFragment(addToBackStack: Boolean, fragmentTag: String, fragment: Fragment) {
        val trans = supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment, fragmentTag)
        if (addToBackStack) trans.addToBackStack(fragmentTag)
        trans.commit()
    }
}
