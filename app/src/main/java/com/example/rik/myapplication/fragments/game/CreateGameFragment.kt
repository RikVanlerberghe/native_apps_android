package com.example.rik.myapplication.fragments.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.GameActivity
import kotlinx.android.synthetic.main.game_create_game.*

class CreateGameFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.game_create_game, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        start_game.setOnClickListener {
            getMainActivity().goTo("play_game")
        }
    }

    private fun getMainActivity(): GameActivity {
        return activity as GameActivity
    }
}