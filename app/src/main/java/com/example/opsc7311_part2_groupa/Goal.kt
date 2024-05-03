package com.example.opsc7311_part2_groupa

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.example.opsc7311_part2_groupa.databinding.ActivityGoalBinding
import com.example.opsc7311_part2_groupa.DBClass.Companion.DATE_GOAL
import com.example.opsc7311_part2_groupa.DBClass.Companion.MAX_HOURS
import com.example.opsc7311_part2_groupa.DBClass.Companion.MIN_HOURS
import com.example.opsc7311_part2_groupa.DBClass.Companion.TABLE_GOALS


class Goal : AppCompatActivity() {
    private lateinit var binding: ActivityGoalBinding
    private val goals = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, goals)
        binding.GoalList.adapter = adapter

        binding.addButton.setOnClickListener {
            val date = binding.addDate.text.toString()
            val minHours = binding.addMinGoal.text.toString().toFloatOrNull()
            val maxHours = binding.editTextNumber.text.toString().toFloatOrNull()

            if (date.isNotEmpty() && minHours != null && maxHours != null) {
                val dbhelp = DBClass(applicationContext)
                val db = dbhelp.writableDatabase

                val data = ContentValues()
                data.put(DATE_GOAL, date)
                data.put(MIN_HOURS, minHours)
                data.put(MAX_HOURS, maxHours)

                val rs: Long = db.insert(TABLE_GOALS, null, data)
                if (rs != -1L) {
                    goals.add("Date: $date, Min Hours: $minHours, Max Hours: $maxHours")
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "Failed to add goal", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields correctly", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        displayGoals()
    }

    private fun displayGoals() {
        val dbhelp = DBClass(applicationContext)
        val db = dbhelp.readableDatabase

        val query = "SELECT * FROM $TABLE_GOALS"
        val rs = db.rawQuery(query, null)

        if (rs.moveToFirst()) {
            val dateIndex = rs.getColumnIndex(DATE_GOAL)
            val minHoursIndex = rs.getColumnIndex(MIN_HOURS)
            val maxHoursIndex = rs.getColumnIndex(MAX_HOURS)

            if (dateIndex != -1 && minHoursIndex != -1 && maxHoursIndex != -1) {
                do {
                    val date = rs.getString(dateIndex)
                    val minHours = rs.getFloat(minHoursIndex)
                    val maxHours = rs.getFloat(maxHoursIndex)
                    goals.add("Date: $date, Min Hours: $minHours, Max Hours: $maxHours")
                } while (rs.moveToNext())
            } else {
            }
        }
        rs.close()
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.goal_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item5 -> {
                startActivity(Intent(this, Time_entry::class.java))
                return true
            }
            R.id.menu_item6 -> {
                startActivity(Intent(this, Homepage::class.java))
                return true
            }

            R.id.menu_item8 -> {
                startActivity(Intent(this, Login::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}