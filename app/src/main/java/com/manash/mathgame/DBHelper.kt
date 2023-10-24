package com.manash.mathgame

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                Score + " TEXT" + ")")

        db.execSQL(query)
     }
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun addName(name : String, score : String ){
        val values = ContentValues()
        values.put(NAME_COl, name)
        values.put(Score, score)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " +
                TABLE_NAME ,null)
        //return db.rawQuery("SELECT * FROM " +
                       //TABLE_NAME+"WHERE AGE>=50",null)
    }

    fun updateCourse(

        name: String, age: String?
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_COl, name)
        values.put(Score, age)
        db.update(TABLE_NAME, values, "NAME=?", arrayOf(name))
        db.close()
    }
    fun deleteDetail(age: String?)
    {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "AGE=?", arrayOf(age))
        db.close()
    }
    companion object{
        private val DATABASE_NAME = "Scorecard"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "my_table1"
        val ID_COL = "id"
        val NAME_COl = "name"
        val Score = "score"
    }
}

