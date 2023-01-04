package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvName: TextView = findViewById(R.id.tv_name)
        val btnSubmit: Button = findViewById(R.id.btn_submit)


        btnSubmit.setOnClickListener{
            if(tvName.text.isEmpty()){
                Toast.makeText(this,"Please Enter Your Name", Toast.LENGTH_LONG).show()
            } else{
                val intent = Intent(this, QuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, tvName.text.toString())
                startActivity(intent)
            }
        }
    }
}