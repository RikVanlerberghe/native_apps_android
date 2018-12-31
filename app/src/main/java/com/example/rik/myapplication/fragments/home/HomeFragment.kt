package com.example.rik.myapplication.fragments.home

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.MainActivity
import kotlinx.android.synthetic.main.home.*

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //check if user is logged in
        if(getMainActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE) == null || getMainActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE).getString("username", "").equals("")){
            try {
                Snackbar.make(view, "You are not logged in", Snackbar.LENGTH_LONG).show()
            }catch (e: Exception){}        }else{
            title_home.text = getMainActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE).getString("username", "")
        }
        //goTo buy card
        buy_card.setOnClickListener {
            getMainActivity().goTo("buyCard")
        }
        //goTo buy drink
        buy_drink.setOnClickListener {
            getMainActivity().goTo("buyDrink")
        }

    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}