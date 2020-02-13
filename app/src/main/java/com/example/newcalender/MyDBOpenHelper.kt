package com.example.newcalender

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.CalendarView

class MyDBOpenHelper(
    context: Context?,
   //name: String?,
    factory: SQLiteDatabase.CursorFactory?
    //version: Int
) : SQLiteOpenHelper(context, name, factory, version) {



    companion object {
        private val version = 1
        private val name = "DB.db"
        val TABLE_NAME = "events"
        val COLUMN_ID = "_id"
        val COLUMN_NAME1 = "date"
        val COLUMN_NAME2 = "event"
        var newStringDateDB = ""
        var monthyear = ""


    }


    override fun onCreate(db: SQLiteDatabase?) {


        val CREATE_PRODUCT_TABLE = ("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME1 + " TEXT," +
                COLUMN_NAME2 + " TEXT" +")")


        db?.execSQL(CREATE_PRODUCT_TABLE)


    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {



        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)

    }

    fun addName(name: Product){

        val values = ContentValues()
        values.put(COLUMN_NAME1, name.date)
        values.put(COLUMN_NAME2, name.event)

        val db = this.writableDatabase
        db.insert(TABLE_NAME,   null, values)
        db.close()
    }



    //get all tables from the database

    fun getAllEvents(): Cursor?{

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)

    }



    fun getDateEvents(): Cursor?{

        val db = this.readableDatabase
        return  db.rawQuery("SELECT * FROM $TABLE_NAME WHERE date = '$newStringDateDB'",null)
    }



    fun getMonthEvents(): Cursor?{

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE date LIKE '%$monthyear%'", null)



    }



}