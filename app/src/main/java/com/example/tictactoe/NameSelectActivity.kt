package com.example.tictactoe
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class NameSelectActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectname)

        //WITH THE TEXTBOXES WE MADE IN XML FILES WE SEARCH FOR THE EACH TEXT BOX ID AND PUT IT IN THE TEXT BOX
        val player1NameEditText = findViewById<EditText>(R.id.player1user)
        val player2NameEditText = findViewById<EditText>(R.id.player2user)
        val startButton = findViewById<Button>(R.id.Button)

        //RETRIEVE THE TEXT AND PUT IT IN THE PLAYER NAMES VARIABLES
        startButton.setOnClickListener {
            val player1Name = player1NameEditText.text.toString()
            val player2Name = player2NameEditText.text.toString()

            //CHECK FOR THE TEXT BOX
            if (player1Name.isEmpty() || player2Name.isEmpty()) {
                Toast.makeText(this, "Enter the names", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, PVPActivity::class.java)
                //ADDED PLAYERS NAMES TO THE INTENT SO WE CAN DISPLAY THEM LATER
                intent.putExtra("PLAYER1_NAME", player1Name)
                intent.putExtra("PLAYER2_NAME", player2Name)
                startActivity(intent)
            }
        }
    }
}
