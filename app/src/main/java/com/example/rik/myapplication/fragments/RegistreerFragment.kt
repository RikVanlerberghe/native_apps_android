package com.example.rik.myapplication.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rik.myapplication.R
import com.example.rik.myapplication.activities.MainActivity
import com.example.rik.myapplication.domain.models.User
import kotlinx.android.synthetic.main.inloggen.*

class RegistreerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.inloggen, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inloggen.setOnClickListener {
            //TODO alle checks
            getMainActivity().db!!.addUser(User(username.text.toString(), password.text.toString(), 0))
        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}