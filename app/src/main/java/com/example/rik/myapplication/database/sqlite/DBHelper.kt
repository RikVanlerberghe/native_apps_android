package com.example.rik.myapplication.database.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.rik.myapplication.domain.models.Card
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
        db!!.execSQL(CREATE_CARD_TABLE)
        db!!.execSQL(CREATE_USER_TABLE)
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

    fun deleteCard(id: String): Boolean {
        val db = this.writableDatabase
        val _succes = db.delete("card", "id" + "=?", arrayOf(id)).toLong()
        db.close()
        return Integer.parseInt("$_succes") != -1
    }

    fun deleteUser(username: String): Boolean {
        val db = this.writableDatabase
        val _succes = db.delete("user", "username" + "=?", arrayOf(username)).toLong()
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
    // Access property for Context
    val Context.database: DBHelper
        get() = DBHelper.getInstance(applicationContext)
}