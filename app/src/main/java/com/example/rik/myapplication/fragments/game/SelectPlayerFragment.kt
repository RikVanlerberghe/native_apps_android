package com.example.rik.myapplication.fragments.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.R.id.players
import com.example.rik.myapplication.activities.GameActivity
import com.example.rik.myapplication.adapters.game.PlayerAdapter
import com.example.rik.myapplication.domain.models.Player
import kotlinx.android.synthetic.main.game_create_game.*

class SelectPlayerFragment: Fragment() {
    private lateinit var adapterList: MutableList<String>
    private lateinit var adapter: PlayerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.game_select_players, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeRecyclerList()

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