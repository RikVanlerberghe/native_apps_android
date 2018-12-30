package com.example.rik.myapplication.fragments.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.GameActivity
import com.example.rik.myapplication.adapters.game.PlayGameAdapter
import com.example.rik.myapplication.domain.models.Player
import kotlinx.android.synthetic.main.game_play_game.*
import kotlinx.android.synthetic.main.game_item_scorebord.*

class PlayGameFragment:Fragment() {

    private lateinit var adapter: PlayGameAdapter
    private lateinit var group: ArrayList<Player>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.game_play_game, container, false)

        group = getMainActivity().getGroup()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeRecyclerList()
    }

    private fun makeRecyclerList() {
        //makes RecyclerList with the adapter
        adapter = PlayGameAdapter(context!!, this, group)
        this.scorebord.adapter = adapter
        this.scorebord.layoutManager = LinearLayoutManager(context)
    }

    private fun getMainActivity(): GameActivity {
        //gets activity
        return activity as GameActivity
    }
}