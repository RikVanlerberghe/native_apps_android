package com.example.rik.myapplication.interfaces.game

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.viewHolders.game.PlayerViewHolder

open class PlayerAdapterInterface (internal var context: Context, internal var fragment: Fragment,
                                   internal var playerList: MutableList<String>)
    : RecyclerView.Adapter<PlayerViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_item_player, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun removeAt(position: Int) {
        //remove at position
        playerList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holderCreate: PlayerViewHolder, position: Int) {
        var member = playerList[position]
        holderCreate.playerName.text = member
    }
}