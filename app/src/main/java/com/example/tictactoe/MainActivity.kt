package com.example.tictactoe
import android.content.Intent
import android.widget.Button
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DEFINING A BUTTON
        val myButton = findViewById<Button>(R.id.button)
        //SETTING AN ACTION ON PRESS
        myButton.setOnClickListener {
            //ON BUTTON PRESS MOVES TO THE NEXT ACTIVITY
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

}