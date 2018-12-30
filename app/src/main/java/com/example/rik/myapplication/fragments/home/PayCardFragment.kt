package com.example.rik.myapplication.fragments.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.MainActivity
import kotlinx.android.synthetic.main.buy_pay_card.*

class PayCardFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.buy_pay_card, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        amountToPay.text = "You have to pay € " + getMainActivity().getAmountToPay()

        pay.setOnClickListener {
            getMainActivity().goTo("home")
        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}
