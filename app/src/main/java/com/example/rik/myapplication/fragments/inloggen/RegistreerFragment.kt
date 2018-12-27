package com.example.rik.myapplication.fragments.inloggen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
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
            //TODO errors aanvullen checkusername gebruiken
            if(reg_password.equals(password_confirm)){
                createUser(User(reg_username.text.toString(), reg_password.text.toString(), 0))
            }else{
                Snackbar.make(view, "passwords are not equal", Snackbar.LENGTH_LONG).show()
            }
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
                        //TODO aanvullen
                    }else{
                        //TODO aanvullen
                        }
                    }
                    is Result.Failure -> {
                        //TODO aanvullen
                   }
            }
        }
    }

    private fun createUser(user: User){
        val request = Api.instance.createUser(user)
        request.responseJson { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    //TODO aanvullen
                }
                is Result.Success -> {
                    val json = result.value.obj()
                    val token = json["token"].toString()
                    //TODO aanvullen
                }
            }
        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}