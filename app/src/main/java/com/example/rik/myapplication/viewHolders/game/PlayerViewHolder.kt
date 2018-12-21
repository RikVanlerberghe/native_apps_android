package com.example.rik.myapplication.viewHolders.game

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.game_player_item.view.*

class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val playerName = itemView.name
}