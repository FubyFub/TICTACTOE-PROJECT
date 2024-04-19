package com.example.tictactoe
import android.widget.Button
import android.content.Intent
import android.app.Activity
import android.os.Bundle


class SecondActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DEFINING THE SCENE WE USE
        setContentView(R.layout.activity_second)

        //THE SAME AS IN MAINACTIVITY
        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, NameSelectActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, PVEActivity::class.java)
            startActivity(intent)
        }
    }
}
