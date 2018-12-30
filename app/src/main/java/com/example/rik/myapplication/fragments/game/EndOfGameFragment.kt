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

        title.text = winner.name + ",\nwon the game!!!"

        reset_players.setOnClickListener {
            //all players socres are reset after a game
            group.forEach { player ->
                player.score = 0
                player.fifteen =0
                player.sixteen = 0
                player.seventeen =0
                player.eighteen = 0
                player.nineteen =0
                player.twenty = 0
                player.bull =0
                getMainActivity().db!!.updatePlayer(player)

                getMainActivity().goTo("create_game")
            }
        }
    }

    private fun getMainActivity(): GameActivity {
        //get the activity
        return activity as GameActivity
    }
}