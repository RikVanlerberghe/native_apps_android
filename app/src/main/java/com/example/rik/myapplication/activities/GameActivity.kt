package com.example.rik.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.example.rik.myapplication.R
import com.example.rik.myapplication.R.id.navHome
import com.example.rik.myapplication.database.sqlite.DBHelper
import com.example.rik.myapplication.domain.models.Player
import com.example.rik.myapplication.fragments.BalanceFragment
import com.example.rik.myapplication.fragments.game.CreateGameFragment
import com.example.rik.myapplication.fragments.game.EndOfGameFragment
import com.example.rik.myapplication.fragments.game.PlayGameFragment
import com.example.rik.myapplication.fragments.game.SelectPlayerFragment
import com.example.rik.myapplication.fragments.settings.InfoFragment
import com.example.rik.myapplication.fragments.settings.SettingsFragment

import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.app_bar_game.*

class GameActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var currentFragmentTag: String = ""
    var db : DBHelper? = null
    private var group: ArrayList<Player> = ArrayList()
    private lateinit var winner: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        db = DBHelper(this)

        setSupportActionBar(toolbarGame)
        goTo("create_game")

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbarGame,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
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
            //menu
            "home" -> {
                startActivity(Intent(this, MainActivity::class.java))
                return
            }
            "balance" -> {
                fragment = BalanceFragment ()
                currentFragmentTag = fragmentName
            }
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
                fragment = PlayGameFragment()
                currentFragmentTag = fragmentName
            }
            "select_players" -> {
                fragment = SelectPlayerFragment()
                currentFragmentTag = fragmentName
            }
            "end_of_game" -> {
                fragment = EndOfGameFragment()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            navHome -> {
                goTo("home")
            }
            R.id.nav_balance -> {
                goTo("balance")
            }
            R.id.nav_registreren -> {
            }
            R.id.nav_inloggen -> {
            }
            R.id.nav_game -> {
            }
            R.id.nav_send -> {
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun getGroup() = group
    fun getWinner() = winner
    fun setGroup(group: ArrayList<Player>){
        this.group = group
    }
    fun setWinner(player: Player){
        this.winner = player
    }

    fun addPlayerToGroup(player: Player){
        group.forEach {p ->
            if(p.name.equals(player.name)){
                error("this player is already in the game")
            }
        }
        this.group.add(player)
    }

    private fun switchFragment(addToBackStack: Boolean, fragmentTag: String, fragment: Fragment) {
        val trans = supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment, fragmentTag)
        if (addToBackStack) trans.addToBackStack(fragmentTag)
        trans.commit()
    }
}
