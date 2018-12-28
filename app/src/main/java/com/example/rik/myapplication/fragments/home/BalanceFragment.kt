package com.example.rik.myapplication.fragments.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.MainActivity
import com.example.rik.myapplication.domain.models.Card
import kotlinx.android.synthetic.main.balance.*

class BalanceFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.balance, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberOfCardsLeft.text = getMainActivity().db!!.getCards().size.toString()
        numberOfDrinksLeft.text = getCurrentCard().numberOfDrinksLeft.toString()
    }

    private fun getCurrentCard(): Card {
        return getMainActivity().db!!.getCards().first()
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}