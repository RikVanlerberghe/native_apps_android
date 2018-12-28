package com.example.rik.myapplication.fragments.inloggen

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
import kotlinx.android.synthetic.main.inloggen_inloggen.*
import kotlinx.android.synthetic.main.inloggen_registreren.*

class InlogFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.inloggen_inloggen, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inloggen.setOnClickListener {
            try{
                if(username.text.toString().isEmpty() || password.text.toString().isEmpty()){
                    Snackbar.make(view, "not all fields are filled", Snackbar.LENGTH_LONG).show()
                }else {
                    loginUser()
                }
            }catch (e : Exception){
                Snackbar.make(view, "Something went wrong..", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun loginUser(){
        val request = Api.instance.login(username.text.toString(), password.text.toString())
        request.responseJson { request, response, result ->
            when(result){
                is Result.Failure -> {
                    Snackbar.make(this.view!!, "user or password are not correct", Snackbar.LENGTH_LONG).show()
                }
                is Result.Success -> {
                    val json = result.value.obj()
                    val token = json["token"].toString()
                    val username = json["username"].toString()
                    val editor = getMainActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE).edit()
                    editor.putString("user", token)
                    editor.putString("username", username)
                    editor.apply()
                    getMainActivity().goTo("home")
                }
            }
        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}