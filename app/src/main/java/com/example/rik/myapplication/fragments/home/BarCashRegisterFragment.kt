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
import com.example.rik.myapplication.network.Api
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.home_balance.*
import kotlinx.android.synthetic.main.home_barcashregister.*

class BarCashRegisterFragment : Fragment() {

    private lateinit var budget: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.home_barcashregister, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var request = Api.instance.getBarCashRegister("Barkas")
        request.responseJson { request, response, result ->
            when(result){
                is Result.Failure -> {
                    Snackbar.make(view, "Something went wrong..", Snackbar.LENGTH_LONG).show()
                }
                is Result.Success -> {
                    val json = result.value.obj()
                    budget = json["budget"].toString()
                    current_state.text = budget
                }
            }
        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}