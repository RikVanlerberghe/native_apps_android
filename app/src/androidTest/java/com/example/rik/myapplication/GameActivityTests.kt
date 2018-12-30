package com.example.rik.myapplication

import android.support.test.runner.AndroidJUnit4
import com.example.rik.myapplication.activities.MainActivity
import android.support.test.espresso.intent.rule.IntentsTestRule
import com.example.rik.myapplication.activities.GameActivity
import com.example.rik.myapplication.domain.models.Player
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.mockito.Mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import java.lang.IllegalStateException

@RunWith(AndroidJUnit4::class)
class GameActivityTests {
    @get:Rule
    val mainActivity = IntentsTestRule<GameActivity>(GameActivity::class.java)

    @Mock
    val group = ArrayList<Player>()

    @Before
    fun init(){
        group.add(Player("Peter"))
        group.add(Player("Mark"))
        mainActivity.activity.setGroup(group)
    }

    @Test
    fun addPlayerToGroupTest(){
        mainActivity.activity.addPlayerToGroup(Player("Thomas"))
        group.add(Player("Thomas"))
        assertEquals(mainActivity.activity.getGroup(), group)
    }

    @Test
    fun addPlayerToGroupAllreadyInGameTest(){
        try {
        mainActivity.activity.addPlayerToGroup(Player("Peter"))
        Assert.fail("should throw error")
        }catch (e: Exception){
            assertEquals("this has to give an error", "this player is already in the game", e.message)
        }
    }
}