package com.example.rik.myapplication.database.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.rik.myapplication.domain.models.Card
import com.example.rik.myapplication.domain.models.Player
import com.example.rik.myapplication.domain.models.User

class DBHelper(context: Context) : SQLiteOpenHelper(context, "database", null, 1) {

    companion object {
        private var instance: DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if (instance == null) {
                instance = DBHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //not implemented
        val CREATE_USER_TABLE = "CREATE TABLE user " + "(username TEXT PRIMARY KEY, password TEXT, balance Integer)"
        db!!.execSQL(CREATE_USER_TABLE)

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CARD_TABLE = "CREATE TABLE card " + "(id Integer PRIMARY KEY, numberOfDrinksLeft Integer)"
        val CREATE_USER_TABLE = "CREATE TABLE user " + "(username TEXT PRIMARY KEY, password TEXT, balance Integer)"
        val CREATE_PLAYER_TABLE = "CREATE TABLE player " + "(name TEXT PRIMARY KEY, fifteen Integer, sixteen Integer, seventeen Integer, eighteen Integer, nineteen Integer, twenty Integer, bull Integer, score Integer)"
        db!!.execSQL(CREATE_CARD_TABLE)
        db!!.execSQL(CREATE_USER_TABLE)
        db!!.execSQL(CREATE_PLAYER_TABLE)
    }



    fun addUser(user: User): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("password", "test")
        values.put("username", "test")
        values.put("balance", 1)
        val _succes = db.insert("user",null,values)
        db.close()
        return (Integer.parseInt("$_succes") != -1)
    }

    fun addCard(): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("numberOfDrinksLeft", 10)
        val _succes = db.insert("card",null,values)
        db.close()
        return (Integer.parseInt("$_succes") != -1)
    }

    fun deletePlayer(name: String): Boolean {
        val db = this.writableDatabase
        val _succes = db.delete("player", "name" + "=?", arrayOf(name)).toLong()
        db.close()
        return Integer.parseInt("$_succes") != -1
    }

    fun deleteUser(username: String): Boolean {
        val db = this.writableDatabase
        val _succes = db.delete("user", "username" + "=?", arrayOf(username)).toLong()
        db.close()
        return Integer.parseInt("$_succes") != -1
    }

    fun deleteCard(id: String): Boolean {
        val db = this.writableDatabase
        val _succes = db.delete("card", "id" + "=?", arrayOf(id)).toLong()
        db.close()
        return Integer.parseInt("$_succes") != -1
    }

    fun updateCard(id: String, change: Int): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("numberOfDrinksLeft", change)
        val _succes = db.update("card", values, "id" + "=?", arrayOf(id)).toLong()
        db.close()
        return Integer.parseInt("$_succes") != -1
    }

    fun getCards(): ArrayList<Card>{
        val cardList = ArrayList<Card>()
        val db = readableDatabase
        val SELECT_CARDS = "SELECT * FROM card"
        val cursor = db.rawQuery(SELECT_CARDS,null)
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    val card = Card()
                    card.id = cursor.getString(cursor.getColumnIndex("id"))
                    card.numberOfDrinksLeft = cursor.getInt(cursor.getColumnIndex("numberOfDrinksLeft"))
                    cardList.add(card)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return cardList
    }

    fun getPlayers(): ArrayList<Player>{
        val playerList = ArrayList<Player>()
        val db = readableDatabase
        val SELECT_PLAYERS = "SELECT * FROM player"
        val cursor = db.rawQuery(SELECT_PLAYERS,null)
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    val player = Player()
                    player.name = cursor.getString(cursor.getColumnIndex("name"))
                    player.fifteen = cursor.getInt(cursor.getColumnIndex("fifteen"))
                    player.sixteen = cursor.getInt(cursor.getColumnIndex("sixteen"))
                    player.seventeen = cursor.getInt(cursor.getColumnIndex("seventeen"))
                    player.eighteen = cursor.getInt(cursor.getColumnIndex("eighteen"))
                    player.nineteen = cursor.getInt(cursor.getColumnIndex("nineteen"))
                    player.twenty = cursor.getInt(cursor.getColumnIndex("twenty"))
                    player.bull = cursor.getInt(cursor.getColumnIndex("bull"))
                    player.score = cursor.getInt(cursor.getColumnIndex("score"))
                    playerList.add(player)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return playerList
    }

    fun addPlayer(player: Player): Boolean{
        getPlayers().forEach({
                u -> if (u.name == player.name) {
            return error("player allready exists")
            }
        })
            val db = this.writableDatabase
            val values = ContentValues()
            values.put("name", player.name)
            values.put("fifteen", player.fifteen)
            values.put("sixteen", player.sixteen)
            values.put("seventeen", player.seventeen)
            values.put("eighteen", player.eighteen)
            values.put("nineteen", player.nineteen)
            values.put("twenty", player.twenty)
            values.put("bull", player.bull)
            values.put("score", player.score)
            val _succes = db.insert("player", null, values)
            db.close()
            return (Integer.parseInt("$_succes") != -1)
    }

    fun getPlayer(name: String): Player{
        getPlayers().forEach({
                u -> if (u.name == name) {
                    return u
                }
        })
        return error("player does not exist")
    }

    fun getUsers(): ArrayList<User>{
        val userList = ArrayList<User>()
        val db = readableDatabase
        val SELECT_USERS = "SELECT * FROM user"
        val cursor = db.rawQuery(SELECT_USERS,null)
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    val user = User()
                    user.username = cursor.getString(cursor.getColumnIndex("username"))
                    user.password = cursor.getString(cursor.getColumnIndex("password"))
                    user.balance = cursor.getInt(cursor.getColumnIndex("balance"))
                    userList.add(user)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return userList
    }

    fun getUser(username: String, password: String): User{
        getUsers().forEach({
            u -> if (u.username == username) {
            if (u.password == password) {
                return u
            }else{
                return error("password is incorrect")
            }
        }else{
           return error("username is incorrect")
        } })
        return error("something went wrong")
    }
    // Access property for Context
    val Context.database: DBHelper
        get() = DBHelper.getInstance(applicationContext)
}