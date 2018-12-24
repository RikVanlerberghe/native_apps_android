package com.example.rik.myapplication.adapters.game

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.domain.models.Player
import com.example.rik.myapplication.fragments.game.PlayGameFragment
import com.example.rik.myapplication.viewHolders.game.PlayGameViewHolder

class PlayGameAdapter(internal var context: Context,
                      internal var fragment: PlayGameFragment,
                      internal var playerList: MutableList<Player>)
    : RecyclerView.Adapter<PlayGameViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayGameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_scorebord_item, parent, false)
        return PlayGameViewHolder(itemView)
    }

    override fun onBindViewHolder(holderCreate: PlayGameViewHolder, position: Int) {
        var member = playerList[position]

        holderCreate.fifteen.text = "15"
        holderCreate.sixteen.text = "15"
        holderCreate.seventeen.text = "15"
        holderCreate.eighteen.text = "15"
        holderCreate.nineteen.text = "15"
        holderCreate.twenty.text = "15"
        holderCreate.bull.text = "15"
        holderCreate.score.text = "15"
        holderCreate.name.text = member.name

    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}