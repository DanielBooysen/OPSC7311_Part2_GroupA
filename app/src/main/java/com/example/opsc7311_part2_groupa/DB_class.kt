package com.example.opsc7311_part2_groupa

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DBClass(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "EpochDatabase"
        const val TABLE_CONTACTS = "user"
        const val KEY_UNAME = "username"
        const val KEY_MAIL = "email"
        const val KEY_PWORD = "password"

        const val TABLE_CATEGORIES = "categories"
        const val CATEGORY = "category"


        const val TABLE_ENTRIES = "entries"
        const val TIME_ENTRY = "time"
        const val CATEGORY_ENTRY = "category"
        const val DATE_ENTRY = "date"
        const val DESCRIPTION_ENTRY = "description"

        private val TABLE_ENTRIES = "entries"
        private val TIME_ENTRY = "time"
        private val CATEGORY_ENTRY = "category"
        private val DATE_ENTRY = "date"
        private val DESCRIPTION_ENTRY = "description"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        val loginDetails = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_UNAME + " TEXT,"
                + KEY_MAIL + " TEXT,"
                + KEY_PWORD + " TEXT" + ")")
        db?.execSQL(loginDetails)
        val categories = ("CREATE TABLE " + TABLE_CATEGORIES + "("
                + CATEGORY + " TEXT" + ")")
        db?.execSQL(categories)
        val timeEntries = ("CREATE TABLE " + TABLE_ENTRIES + "("
                + TIME_ENTRY + " TEXT,"
                + CATEGORY_ENTRY + " TEXT,"
                + DATE_ENTRY + " TEXT,"
                + DESCRIPTION_ENTRY + " TEXT,"
                + "FOREIGN KEY(" + CATEGORY_ENTRY + ") REFERENCES " + TABLE_CATEGORIES + "(" + CATEGORY + ")" + ")")
        db?.execSQL(timeEntries)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORIES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ENTRIES")
        onCreate(db)
    }
}
