package com.example.rik.myapplication.viewHolders.game

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.game_item_scorebord.view.*

class PlayGameViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    //viewholder holds name and score
    val name = itemView.name

    val fifteen = itemView.fifteen
    val sixteen = itemView.sixteen
    val seventeen = itemView.seventeen
    val eighteen = itemView.eighteen
    val nineteen = itemView.nineteen
    val twenty = itemView.twenty
    val bull = itemView.bull

    val score = itemView.score
}