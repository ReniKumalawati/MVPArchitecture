package com.example.myapp.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.myapp.Impl.Assignment
import java.util.*
import kotlin.collections.ArrayList
import io.reactivex.Observable

class DBOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val cretaeAssignmentTable = ("create table " +
                TABLE_NAME + " (" + COLUMN_ID + "INTERGER PRIMARY KEY," + COLUMN_TITLE + " TEXT," +
                COLUMN_SEVERITY + " TEXT," + COLUMN_DESCRIPTION + " TEXT )")
        db?.execSQL(cretaeAssignmentTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXIST " + TABLE_NAME)
        onCreate(db)
    }

     fun addAssignmentToDB(assignment: Assignment) {
        val values = ContentValues()
        values.put(COLUMN_TITLE, assignment.title)
        values.put(COLUMN_SEVERITY, assignment.severity)
        values.put(COLUMN_DESCRIPTION, assignment.description)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun findAllAssignment(): Observable<ArrayList<Assignment>> {
        var listAssignment = ArrayList<Assignment>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val assignmnet = Assignment()
                    assignmnet.title = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_TITLE))
                    assignmnet.description = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_DESCRIPTION))
                    assignmnet.severity = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_SEVERITY))
                    listAssignment.add(assignmnet)
                }  while (cursor.moveToNext())
            }
        }
        return Observable.just(listAssignment)
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "ingetin.db"
        val TABLE_NAME = "assignment"
        val COLUMN_ID = "_id"
        val COLUMN_TITLE = "title"
        val COLUMN_SEVERITY = "severity"
        val COLUMN_DESCRIPTION = "description"
    }
}