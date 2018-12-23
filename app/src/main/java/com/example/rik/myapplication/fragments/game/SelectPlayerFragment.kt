package com.example.rik.myapplication.fragments.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.R.id.players
import com.example.rik.myapplication.activities.GameActivity
import com.example.rik.myapplication.adapters.game.PlayerAdapter
import com.example.rik.myapplication.domain.models.Player
import com.example.rik.myapplication.interfaces.game.SwipeToDelete
import kotlinx.android.synthetic.main.game_create_game.*
import kotlinx.android.synthetic.main.game_player_item.view.*

class SelectPlayerFragment: Fragment() {

    private lateinit var adapterList: MutableList<String>
    private lateinit var adapter: PlayerAdapter
    private lateinit var group: ArrayList<Player>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.game_select_players, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeRecyclerList()

        val swipeHandler = object : SwipeToDelete(context) {
            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                try{
                    getMainActivity().db!!.deletePlayer(adapterList.get(p0.adapterPosition))
                }catch (e: Exception){
                    //TODO exception aanvullen
                }
                adapter.removeAt(p0.adapterPosition)
                editAdapterList()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(players)

    }

    private fun makeRecyclerList() {
        adapterList = ArrayList()
        getMainActivity().db!!.getPlayers().forEach { player ->
            adapterList.add(player.name)
        }
        adapter = PlayerAdapter(context!!, this, adapterList)
        players.adapter = adapter
        players.layoutManager = LinearLayoutManager(context)
    }

    private fun editAdapterList() {
        players.adapter = adapter
        adapter.notifyDataSetChanged()
        players.setHasFixedSize(true)
        players.layoutManager = LinearLayoutManager(context)
    }

    private fun getMainActivity(): GameActivity {
        return activity as GameActivity
    }
}