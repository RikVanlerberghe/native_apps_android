package com.example.rik.myapplication.adapters.game

import android.content.Context
import android.support.v4.app.Fragment
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

    lateinit var activity: GameActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayGameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_scorebord_item, parent, false)
        activity = fragment.activity as GameActivity
        return PlayGameViewHolder(itemView)
    }

    override fun onBindViewHolder(holderCreate: PlayGameViewHolder, position: Int) {
        var member = playerList[position]

        holderCreate.fifteen.text = member.fifteen.toString()
        holderCreate.sixteen.text = member.sixteen.toString()
        holderCreate.seventeen.text = member.seventeen.toString()
        holderCreate.eighteen.text = member.eighteen.toString()
        holderCreate.nineteen.text = member.nineteen.toString()
        holderCreate.twenty.text = member.twenty.toString()
        holderCreate.bull.text = member.bull.toString()
        holderCreate.score.text = member.score.toString()
        holderCreate.name.text = member.name

        holderCreate.fifteen.setOnClickListener {
            if (member.fifteen < 3) {
                member.fifteen++
                activity.db!!.updatePlayer(member)
                holderCreate.fifteen.text = member.fifteen.toString()
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
        holderCreate.sixteen.setOnClickListener {
            if (member.sixteen < 3) {
                member.sixteen++
                activity.db!!.updatePlayer(member)
                holderCreate.sixteen.text = member.sixteen.toString()
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
        holderCreate.seventeen.setOnClickListener {
            if (member.seventeen < 3) {
                member.seventeen++
                activity.db!!.updatePlayer(member)
                holderCreate.seventeen.text = member.seventeen.toString()
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
        holderCreate.eighteen.setOnClickListener {
            if (member.eighteen < 3) {
                member.eighteen++
                activity.db!!.updatePlayer(member)
                holderCreate.eighteen.text = member.eighteen.toString()
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
        holderCreate.nineteen.setOnClickListener {
            if (member.nineteen < 3) {
                member.nineteen++
                activity.db!!.updatePlayer(member)
                holderCreate.nineteen.text = member.nineteen.toString()
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
        holderCreate.twenty.setOnClickListener {
            if (member.twenty < 3) {
                member.twenty++
                activity.db!!.updatePlayer(member)
                holderCreate.twenty.text = member.twenty.toString()
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
        holderCreate.bull.setOnClickListener {
            if (member.bull < 3) {
                member.bull++
                activity.db!!.updatePlayer(member)
                holderCreate.bull.text = member.bull.toString()
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
            //TODO naar winnaarscherm
            member.name = member.name + " winner"
            activity.db!!.updatePlayer(member)
            this.notifyDataSetChanged()
        }
    }
}