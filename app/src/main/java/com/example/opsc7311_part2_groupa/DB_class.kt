package com.example.opsc7311_part2_groupa

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DBClass(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "EpochDatabase"
        private val TABLE_CONTACTS = "user"
        private val KEY_UNAME = "username"
        private val KEY_MAIL = "email"
        private val KEY_PWORD = "password"

        private val TABLE_CATEGORIES = "categories"
        private val CATEGORY = "category"

        private val TABLE_ENTRIES = "entries"
        private val TIME_ENTRY = "time"
        private val CATEGORY_ENTRY = "category"
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
                + "FOREIGN KEY(" + CATEGORY_ENTRY + ") REFERENCES " + TABLE_CATEGORIES + "(" + CATEGORY + ")" + ")")
        db?.execSQL(timeEntries)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES)
        onCreate(db)
    }
}
