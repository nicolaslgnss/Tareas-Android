package com.example.tareas.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.tareas.utils.DatabaseManager

class TaskDAO(val context: Context) {

    private val dbManager = DatabaseManager(context)
    private lateinit var db: SQLiteDatabase

    fun open() {
        db = dbManager.writableDatabase
    }

    fun close() {
        dbManager.close()
    }

    private fun cursorToEntity(cursor: Cursor): Task {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_TITLE))
        val done = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DONE)) == 1
        return Task(id, title, done)
    }


    fun findById(id: Int): Task? {
        open()
        var task: Task? = null
        var cursor: Cursor? = null
        try {
            cursor = db.query(
                Task.TABLE_NAME,
                null,
                "${Task.COLUMN_NAME_ID} = ?",
                arrayOf(id.toString()),
                null,
                null,
                null
            )
            if (cursor.moveToNext()) {
                task = cursorToEntity(cursor)
            }
        } catch (e: Exception) {
            Log.e("DATABASE", e.localizedMessage, e)
        } finally {
            cursor?.close()
            close()
        }
        return task
    }

    fun findAll(): List<Task> {
        open()
        val taskList = mutableListOf<Task>()
        var cursor: Cursor? = null
        try {
            cursor = db.query(
                Task.TABLE_NAME,                  // The table to query
                null,                             // The array of columns to return (pass null to get all)
                null,                             // The columns for the WHERE clause
                null,                             // The values for the WHERE clause
                null,                             // Don't group the rows
                null,                             // Don't filter by row groups
                null                              // The sort order
            )

            while (cursor.moveToNext()) {
                val task = cursorToEntity(cursor)
                taskList.add(task)
            }
        } catch (e: Exception) {
            Log.e("DATABASE", e.localizedMessage, e)
        } finally {
            cursor?.close()
            close()
        }

        return taskList
    }
}