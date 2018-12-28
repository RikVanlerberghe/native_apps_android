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
import com.example.rik.myapplication.domain.models.Card
import com.example.rik.myapplication.network.Api
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.buy_card.*

class BuyDrinkFragment : Fragment() {

    //number of drinks to cross off
    private var numberOfDrinks: Int = 1
    private lateinit var username: String
    private lateinit var budget: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.buy_drink, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user = getMainActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE).getString("username", "niet ingelogd")
        var request = Api.instance.getUser(user)
        request.responseJson { request, response, result ->
            when(result){
                is Result.Failure -> {
                    Snackbar.make(view, "You are not logged in", Snackbar.LENGTH_LONG).show()
                }
                is Result.Success -> {
                    val json = result.value.obj()
                    username = json["username"].toString()
                    budget = json["budget"].toString()
                }
            }
        }
        increase.setOnClickListener {
            numberOfDrinks++
            quantity.setText(numberOfDrinks.toString())
        }
        decrease.setOnClickListener {
            numberOfDrinks--
            quantity.setText(numberOfDrinks.toString())
        }
        confirm.setOnClickListener {
            var x = budget.toDouble()
            var y = numberOfDrinks
            if(x >= y){
                var newBudget = x - y
                var request = Api.instance.updateUser(username, newBudget.toInt().toString())
                request.responseJson { request, response, result ->
                    when(result){
                        is Result.Failure -> {
                            Snackbar.make(view, "something went wrong..", Snackbar.LENGTH_LONG).show()
                        }
                        is Result.Success -> {
                            val json = result.value.obj()
                            username = json["username"].toString()
                            budget = json["budget"].toString()
                            getMainActivity().goTo("home")
                        }
                    }
                }
            }else{
                Snackbar.make(view, "You don't have enough money", Snackbar.LENGTH_LONG).show()
            }

        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}