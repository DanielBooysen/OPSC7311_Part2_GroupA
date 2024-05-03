package com.example.opsc7311_part2_groupa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class List_view : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve timesheet entries from the database
        val entriesList = getTimesheetEntriesFromDatabase()

        // Create and set adapter
        val adapter = EntryAdapter(entriesList)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    @SuppressLint("Range")
    private fun getTimesheetEntriesFromDatabase(): List<TimeSheetEntry> {
        val entries = mutableListOf<TimeSheetEntry>()

        val db = DBClass(applicationContext).readableDatabase
        val query = "SELECT * FROM ${DBClass.TABLE_ENTRIES}"
        db.rawQuery(query, null).use { cursor ->
            while (cursor.moveToNext()) {
                val startTime = cursor.getString(cursor.getColumnIndex(DBClass.TIME_ENTRY))
                val category = cursor.getString(cursor.getColumnIndex(DBClass.CATEGORY_ENTRY))
                val description = cursor.getString(cursor.getColumnIndex(DBClass.DESCRIPTION_ENTRY))
                val date = cursor.getString(cursor.getColumnIndex(DBClass.DATE_ENTRY))

                val entry = TimeSheetEntry(startTime, category, description, date)
                entries.add(entry)
            }
        }
        db.close()
        // Log the retrieved entries
        for (entry in entries) {
            Log.d("DB_ENTRIES", entry.toString())
        }

        return entries
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item1 -> {
                startActivity(Intent(this, Time_entry::class.java))
                return true
            }

            R.id.menu_item2 -> {
                startActivity(Intent(this, Goal::class.java))
                return true
            }

            R.id.menu_item3 -> {
                startActivity(Intent(this, Login::class.java))
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}