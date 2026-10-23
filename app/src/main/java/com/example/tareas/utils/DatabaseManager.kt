package com.example.tareas.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tareas.data.Task

class DatabaseManager(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Task.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onDestroy(db)
        onCreate(db)
    }

    fun onDestroy(db: SQLiteDatabase){
        db.execSQL(Task.SQL_CREATE_TABLE)
    }

    companion object{
        // If you change the database schema , you must increment the database version.

        const val DATABASE_VERSION = 1

        const val DATABASE_NAME = "Tareas.db"


    }
}