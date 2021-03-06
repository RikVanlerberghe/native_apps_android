package com.example.rik.myapplication.fragments.game

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.GameActivity
import com.example.rik.myapplication.adapters.game.CreateGameAdapter
import com.example.rik.myapplication.domain.models.Player
import com.example.rik.myapplication.interfaces.game.SwipeToDeleteInterface
import kotlinx.android.synthetic.main.game_create_game.*
import java.lang.Exception

class CreateGameFragment: Fragment() {

    private lateinit var adapterList: MutableList<String>
    private lateinit var adapter: CreateGameAdapter
    private lateinit var group: ArrayList<Player>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.game_create_game, container, false)

        group = getMainActivity().getGroup()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeRecyclerList()

        add_from_list.setOnClickListener {
            getMainActivity().goTo("select_players")
        }

        add_player.setOnClickListener {
            //puts player in db and in the group
            try{
                getMainActivity().db!!.addPlayer(Player(player_name.text.toString()))
                adapterList.add(player_name.text.toString())
                group.add(Player(player_name.text.toString()))
                getMainActivity().setGroup(group)
            }catch (e: Exception){
                Snackbar.make(view, "this player already exist", Snackbar.LENGTH_LONG).show()
            }
            player_name.setText("")
            editAdapterList()
        }

        start_game.setOnClickListener {
            getMainActivity().goTo("play_game")
        }

        val swipeHandler = object : SwipeToDeleteInterface(context) {
            //deletes a player by swiping
            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                adapterList.removeAt(p0.adapterPosition)
                group.removeAt(p0.adapterPosition)
                getMainActivity().setGroup(group)
                editAdapterList()
            }
        }

        //swipe delete
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(players)
    }

    private fun makeRecyclerList() {
        //makes RecyclerList with the adapter
        adapterList = groupToAdapterList()
        adapter = CreateGameAdapter(context!!, this, adapterList)
        players.adapter = adapter
        players.layoutManager = LinearLayoutManager(context)
    }

    private fun editAdapterList() {
        //edits list
        players.adapter = adapter
        adapter.notifyDataSetChanged()
        players.setHasFixedSize(true)
        players.layoutManager = LinearLayoutManager(context)
    }

    private fun groupToAdapterList(): MutableList<String>{
        //changes group to adapterlist
        var playerNames : MutableList<String> = ArrayList()
        group.forEach {
            player -> playerNames.add(player.name)
        }

        return playerNames
    }

    private fun getMainActivity(): GameActivity {
        //get the Activity
        return activity as GameActivity
    }
}