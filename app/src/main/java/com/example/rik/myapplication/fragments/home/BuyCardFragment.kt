package com.example.rik.myapplication.fragments.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.MainActivity
import kotlinx.android.synthetic.main.buy_card.*

class BuyCardFragment : Fragment() {

    //number of cards to order
    private var numberOfCards: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.buy_card, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        increase.setOnClickListener {
            numberOfCards++
            quantity.setText(numberOfCards.toString())
        }
        decrease.setOnClickListener {
            numberOfCards--
            quantity.setText(numberOfCards.toString())
        }
        confirm.setOnClickListener {
            //TODO backend verder uitwerken
            for(i in 1..numberOfCards) {
                getMainActivity().db!!.addCard()
            }
            getMainActivity().goTo("home")
        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}