package com.example.opsc7311_part2_groupa


import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.opsc7311_part2_groupa.databinding.ActivityMainBinding


class Main : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbhelp=DBClass(applicationContext)
        var db=dbhelp.writableDatabase
        binding.rRegister.setOnClickListener{
            val username = binding.username.text.toString()
            val email =binding.email.text.toString()
            val password =  binding.Password.text.toString()
            if(username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                val data = ContentValues()
                data.put("username", binding.username.text.toString())
                data.put("email", binding.email.text.toString())
                data.put("password", binding.Password.text.toString())
                val rs:Long = db.insert("user", null, data)
                if(!rs.equals(-1)){
                    val ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Account has been registered successfully")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.username.text.clear()
                    binding.email.text.clear()
                    binding.Password.text.clear()
                }else{
                    val ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("User not registered")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.username.text.clear()
                    binding.email.text.clear()
                    binding.Password.text.clear()
                }
            }else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }
        binding.login.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}