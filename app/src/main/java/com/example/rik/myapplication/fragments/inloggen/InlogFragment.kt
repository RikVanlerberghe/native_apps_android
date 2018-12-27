package com.example.rik.myapplication.fragments.inloggen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.MainActivity
import com.example.rik.myapplication.network.Api
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.inloggen_inloggen.*

class InlogFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.inloggen_inloggen, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inloggen.setOnClickListener {
            //TODO inloggen_inloggen en savedinstancestate
            try{
                loginUser()
                //getMainActivity().db!!.getUser(username.text.toString(), password.text.toString())
                //getMainActivity().goTo("home")
            }catch (e : Exception){

            }
        }
    }

    private fun loginUser(){
        val request = Api.instance.login(username.text.toString(), password.text.toString())
        request.responseJson { request, response, result ->
            when(result){
                is Result.Failure -> {
                    //TODO aanvullen
                }
                is Result.Success -> {
                    //TODO aanvullen
                    getMainActivity().goTo("home")
                }
            }
        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}