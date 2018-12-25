package com.example.rik.myapplication.adapters.game

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import com.example.rik.myapplication.activities.GameActivity
import com.example.rik.myapplication.fragments.game.SelectPlayerFragment
import com.example.rik.myapplication.interfaces.game.PlayerAdapterInterface
import com.example.rik.myapplication.viewHolders.game.PlayerViewHolder

class SelectPlayerAdapter(context: Context, fragment: SelectPlayerFragment, playerList: MutableList<String>) :
    PlayerAdapterInterface(context, fragment, playerList) {


    override fun onBindViewHolder(holderCreate: PlayerViewHolder, position: Int) {
        var member = playerList[position]
        holderCreate.playerName.text = member

        holderCreate.itemView.setOnClickListener{
            run {

                var activity = fragment.activity as GameActivity

                try {
                    var x = playerList[position]
                    var player = activity.db!!.getPlayer(playerList[position])
                    activity.addPlayerToGroup(player)
                    activity.goTo("create_game")
                }catch (e: Exception){
                    Snackbar.make(fragment.view!!, "this player is already in the game", Snackbar.LENGTH_LONG).show()
                    //error("didn't add to group")
                }

            }
        }
    }
}