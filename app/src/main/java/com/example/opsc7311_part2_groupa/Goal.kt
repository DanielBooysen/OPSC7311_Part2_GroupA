package com.example.opsc7311_part2_groupa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class Goal : AppCompatActivity() {

    private val goals = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
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