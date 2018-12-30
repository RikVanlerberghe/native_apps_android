package com.example.rik.myapplication

import android.support.test.espresso.intent.rule.IntentsTestRule
import com.android.dx.command.Main
import com.example.rik.myapplication.activities.MainActivity
import com.example.rik.myapplication.domain.models.Player
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class MainActivityTests {
    @get:Rule
    val mainActivity = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Mock
    val amountToPay = "15.0"

    @Before
    fun init(){
        mainActivity.activity.setAmountToPay(amountToPay)
    }

    @Test
    fun getAmountToPayTest(){
        assertEquals(mainActivity.activity.getAmountToPay(), "15.0")
    }

    @Test
    fun setAmountToPayTest(){
        mainActivity.activity.setAmountToPay("30.0")
        assertEquals(mainActivity.activity.getAmountToPay(), "30.0")
    }

}