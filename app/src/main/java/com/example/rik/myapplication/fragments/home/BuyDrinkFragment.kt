package com.example.rik.myapplication.fragments.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.MainActivity
import com.example.rik.myapplication.domain.models.Card
import kotlinx.android.synthetic.main.buy_card.*

class BuyDrinkFragment : Fragment() {

    //number of drinks to cross off
    private var numberOfDrinks: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.buy_drink, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        increase.setOnClickListener {
            numberOfDrinks++
            quantity.setText(numberOfDrinks.toString())
        }
        decrease.setOnClickListener {
            numberOfDrinks--
            quantity.setText(numberOfDrinks.toString())
        }
        confirm.setOnClickListener {
            //TODO backend
            while (numberOfDrinks > getCurrentCard().numberOfDrinksLeft) {
                numberOfDrinks -= getCurrentCard().numberOfDrinksLeft
                getMainActivity().db!!.deleteCard(getCurrentCard().id)
            }
            if (numberOfDrinks > 0) {
                getMainActivity().db!!.updateCard(
                    getCurrentCard().id,
                    getCurrentCard().numberOfDrinksLeft - numberOfDrinks
                )
            }
            getMainActivity().goTo("home")
        }
    }

    private fun getCurrentCard(): Card {
        return getMainActivity().db!!.getCards().first()
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}