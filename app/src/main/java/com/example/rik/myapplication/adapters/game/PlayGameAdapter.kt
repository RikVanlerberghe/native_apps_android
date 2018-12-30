package com.example.rik.myapplication.adapters.game

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.GameActivity
import com.example.rik.myapplication.domain.models.Player
import com.example.rik.myapplication.fragments.game.PlayGameFragment
import com.example.rik.myapplication.viewHolders.game.PlayGameViewHolder

class PlayGameAdapter(internal var context: Context,
                      internal var fragment: PlayGameFragment,
                      internal var playerList: MutableList<Player>)
    : RecyclerView.Adapter<PlayGameViewHolder>() {
    //handles most of the logic during the game
    lateinit var activity: GameActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayGameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_item_scorebord, parent, false)
        activity = fragment.activity as GameActivity
        return PlayGameViewHolder(itemView)
    }

    override fun onBindViewHolder(holderCreate: PlayGameViewHolder, position: Int) {
        var member = playerList[position]

        //set the scorebord on the correct images
        holderCreate.fifteen.setImageDrawable(selectImage(member.fifteen))
        holderCreate.sixteen.setImageDrawable(selectImage(member.sixteen))
        holderCreate.seventeen.setImageDrawable(selectImage(member.seventeen))
        holderCreate.eighteen.setImageDrawable(selectImage(member.eighteen))
        holderCreate.nineteen.setImageDrawable(selectImage(member.nineteen))
        holderCreate.twenty.setImageDrawable(selectImage(member.twenty))
        holderCreate.bull.setImageDrawable(selectImage(member.bull))
        holderCreate.score.text = member.score.toString()
        holderCreate.name.text = member.name

        //logic for extra fifteen of player
        holderCreate.fifteen.setOnClickListener {
            if (member.fifteen < 3) {
                member.fifteen++
                activity.db!!.updatePlayer(member)
                holderCreate.fifteen.setImageDrawable(selectImage(member.fifteen))
            } else {
                playerList.forEach { p ->
                    if (p.fifteen < 3) {
                        p.score += 15
                        activity.db!!.updatePlayer(p)
                        this.notifyDataSetChanged()
                    }
                }
            }
            checkIfWon(member)
        }
        //logic for extra sixteen of player
        holderCreate.sixteen.setOnClickListener {
            if (member.sixteen < 3) {
                member.sixteen++
                activity.db!!.updatePlayer(member)
                holderCreate.sixteen.setImageDrawable(selectImage(member.sixteen))
            } else {
                playerList.forEach { p ->
                    if (p.sixteen < 3) {
                        p.score += 16
                        activity.db!!.updatePlayer(p)
                        this.notifyDataSetChanged()
                    }
                }
            }
            checkIfWon(member)
        }
        //logic for extra seventeen of player
        holderCreate.seventeen.setOnClickListener {
            if (member.seventeen < 3) {
                member.seventeen++
                activity.db!!.updatePlayer(member)
                holderCreate.seventeen.setImageDrawable(selectImage(member.seventeen))
            } else {
                playerList.forEach { p ->
                    if (p.seventeen < 3) {
                        p.score += 17
                        activity.db!!.updatePlayer(p)
                        this.notifyDataSetChanged()
                    }
                }
            }
            checkIfWon(member)
        }
        //logic for extra eighteen of player
        holderCreate.eighteen.setOnClickListener {
            if (member.eighteen < 3) {
                member.eighteen++
                activity.db!!.updatePlayer(member)
                holderCreate.eighteen.setImageDrawable(selectImage(member.eighteen))
            } else {
                playerList.forEach { p ->
                    if (p.eighteen < 3) {
                        p.score += 18
                        activity.db!!.updatePlayer(p)
                        this.notifyDataSetChanged()
                    }
                }
            }
            checkIfWon(member)
        }
        //logic for extra nineteen of player
        holderCreate.nineteen.setOnClickListener {
            if (member.nineteen < 3) {
                member.nineteen++
                activity.db!!.updatePlayer(member)
                holderCreate.nineteen.setImageDrawable(selectImage(member.nineteen))
            } else {
                playerList.forEach { p ->
                    if (p.nineteen < 3) {
                        p.score += 19
                        activity.db!!.updatePlayer(p)
                        this.notifyDataSetChanged()
                    }
                }
            }
            checkIfWon(member)
        }
        //logic for extra twenty of player
        holderCreate.twenty.setOnClickListener {
            if (member.twenty < 3) {
                member.twenty++
                activity.db!!.updatePlayer(member)
                holderCreate.twenty.setImageDrawable(selectImage(member.twenty))
            } else {
                playerList.forEach { p ->
                    if (p.twenty < 3) {
                        p.score += 20
                        activity.db!!.updatePlayer(p)
                        this.notifyDataSetChanged()
                    }
                }
            }
            checkIfWon(member)
        }
        //logic for extra bull of player
        holderCreate.bull.setOnClickListener {
            if (member.bull < 3) {
                member.bull++
                activity.db!!.updatePlayer(member)
                holderCreate.bull.setImageDrawable(selectImage(member.bull))
            } else {
                playerList.forEach { p ->
                    if (p.bull < 3) {
                        p.score += 25
                        activity.db!!.updatePlayer(p)
                        this.notifyDataSetChanged()
                    }
                }
            }
            checkIfWon(member)
        }

    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    //checks if the player has won the game
    fun checkIfWon(member: Player) {
        if (member.fifteen.equals(3) &&
            member.sixteen.equals(3) &&
            member.seventeen.equals(3) &&
            member.eighteen.equals(3) &&
            member.nineteen.equals(3) &&
            member.twenty.equals(3) &&
            member.bull.equals(3)) {
            playerList.forEach { p ->
                if(p.score < member.score){
                    return
                }
            }
            activity.setWinner(member)
            activity.goTo("end_of_game")
        }
    }

    //sets correct image on the scorebord
    fun selectImage(number: Int): Drawable? {
        when{
            number.equals(0) -> return ResourcesCompat.getDrawable(activity.resources, R.drawable.dart_null, null)
            number.equals(1) -> return ResourcesCompat.getDrawable(activity.resources, R.drawable.dart_first, null)
            number.equals(2) -> return ResourcesCompat.getDrawable(activity.resources, R.drawable.dart_second, null)
            number.equals(3) -> return ResourcesCompat.getDrawable(activity.resources, R.drawable.dart_third, null)
            else -> return ResourcesCompat.getDrawable(activity.resources, R.drawable.dart_null, null)
        }
    }
}