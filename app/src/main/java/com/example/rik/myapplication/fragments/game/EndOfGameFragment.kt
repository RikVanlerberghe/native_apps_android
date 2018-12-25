package com.example.rik.myapplication.fragments.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.GameActivity
import com.example.rik.myapplication.domain.models.Player
import kotlinx.android.synthetic.main.game_end_of_game.*

class EndOfGameFragment: Fragment() {

    private lateinit var group: ArrayList<Player>
    private lateinit var winner: Player

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.game_end_of_game, container, false)

        group = getMainActivity().getGroup()
        winner = getMainActivity().getWinner()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title.text = winner.name + " won the game!!!"
    }

    private fun getMainActivity(): GameActivity {
        return activity as GameActivity
    }
}