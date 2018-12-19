package com.example.rik.myapplication.database.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.rik.myapplication.domain.models.Card

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
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CARD_TABLE = "CREATE TABLE card " + "(id Integer PRIMARY KEY, numberOfDrinksLeft Integer)"
        db!!.execSQL(CREATE_CARD_TABLE)
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
        val SELECT_NUMBER_OF_CARDS = "SELECT * FROM card"
        val cursor = db.rawQuery(SELECT_NUMBER_OF_CARDS,null)
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
    // Access property for Context
    val Context.database: DBHelper
        get() = DBHelper.getInstance(applicationContext)
}