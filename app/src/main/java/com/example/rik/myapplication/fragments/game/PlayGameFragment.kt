package com.example.rik.myapplication.fragments.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.GameActivity
import com.example.rik.myapplication.adapters.game.PlayGameAdapter
import com.example.rik.myapplication.domain.models.Player

class PlayGameFragment:Fragment() {

    private lateinit var adapter: PlayGameAdapter
    private lateinit var adapterList: MutableList<String>
    private lateinit var group: ArrayList<Player>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.game_play_game, container, false)

        group = getMainActivity().getGroup()
        makeRecyclerList()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun makeRecyclerList() {
        adapterList = ArrayList()
        group.forEach { player ->
            adapterList.add(player.name)
        }
        adapter = PlayGameAdapter(context!!, this, adapterList)
    }

    private fun getMainActivity(): GameActivity {
        return activity as GameActivity
    }
}