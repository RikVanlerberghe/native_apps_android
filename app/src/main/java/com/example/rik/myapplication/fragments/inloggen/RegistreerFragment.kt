package com.example.rik.myapplication.fragments.inloggen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.MainActivity
import com.example.rik.myapplication.domain.models.User
import com.example.rik.myapplication.network.Api
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.inloggen_inloggen.*
import kotlinx.android.synthetic.main.inloggen_registreren.*

class RegistreerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.inloggen_registreren, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registreren.setOnClickListener {
            //TODO alle checks
            checkUserName("Jakhals")
            //getMainActivity().db!!.addUser(User(username.text.toString(), password.text.toString(), 0))
        }
    }

    fun checkUserName(input: String){
        val request = Api.instance.checkUsername(input)
        request.responseJson { request, response, result ->
            when(result) {
                is Result.Success -> {
                    var x = String(response.data)
                    val json = JsonParser().parse(x) as JsonObject
                    var exists = json["username"].asString
                    if(exists == "ok"){
                        textView2.text = "ok"
                    }else{
                        if(exists == "alreadyexists"){
                            textView2.text = "alreadyexists"
                        }
                    }
                }
                is Result.Failure -> {
                    error("failure")
                }
            }
        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}