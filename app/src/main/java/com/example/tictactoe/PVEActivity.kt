package com.example.tictactoe
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random


//MAJORITY OF THE CODE IS IDENTICALL TO THE PVPACTIVITY
class PVEActivity : Activity() {

    private val grid = Array(3) { Array(3) { "" } }
    private var player1Turn = true
    private var moves = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pve)

        val buttonLU: Button = findViewById(R.id.buttonLU)
        val buttonLM: Button = findViewById(R.id.buttonLM)
        val buttonLD: Button = findViewById(R.id.buttonLD)
        val buttonMU: Button = findViewById(R.id.buttonMU)
        val buttonMM: Button = findViewById(R.id.buttonMM)
        val buttonMD: Button = findViewById(R.id.buttonMD)
        val buttonRU: Button = findViewById(R.id.buttonRU)
        val buttonRM: Button = findViewById(R.id.buttonRM)
        val buttonRD: Button = findViewById(R.id.buttonRD)


        buttonLU.setOnClickListener { onButtonClick(0, 0, buttonLU) }
        buttonLM.setOnClickListener { onButtonClick(1, 0, buttonLM) }
        buttonLD.setOnClickListener { onButtonClick(2, 0, buttonLD) }
        buttonMU.setOnClickListener { onButtonClick(0, 1, buttonMU) }
        buttonMM.setOnClickListener { onButtonClick(1, 1, buttonMM) }
        buttonMD.setOnClickListener { onButtonClick(2, 1, buttonMD) }
        buttonRU.setOnClickListener { onButtonClick(0, 2, buttonRU) }
        buttonRM.setOnClickListener { onButtonClick(1, 2, buttonRM) }
        buttonRD.setOnClickListener { onButtonClick(2, 2, buttonRD) }
    }

    private fun onButtonClick(row: Int, col: Int, button: Button) {
        if (grid[row][col].isNotEmpty()) return

        val currentPlayer = if (player1Turn) "X" else "O"
        grid[row][col] = currentPlayer
        button.text = currentPlayer
        button.textSize = 48f

        val color = if (player1Turn) android.R.color.holo_red_light else android.R.color.holo_blue_light
        button.setTextColor(resources.getColor(color, null))

        if (checkWin(row, col, currentPlayer)) {
            displayMessage("Player $currentPlayer wins!")
            resetGame()
        } else if (++moves == 9) {
            displayMessage("Draw!")
            resetGame()
        } else {
            player1Turn = !player1Turn
            //IF NOT A HUMAN TURN WE INVOKE 2 OTHER METHODS
            if (!player1Turn) {
                val computerMove = generateComputerMove()
                val computerButton = getButtonForPosition(computerMove.first, computerMove.second)
                onButtonClick(computerMove.first, computerMove.second, computerButton)
            }
        }
    }
//A METHOD FOR A COMPUTER TO GENERATE A RANDOM MOVE USING KOTLIN RANDOM IMPORT
    private fun generateComputerMove(): Pair<Int, Int> {

        var row: Int
        var col: Int
        //RANDOMLY SELECTS A RANDOM TURN FOR THE COMPUTER, IF THE CELL IS OCCUPIED IT REROLLS
        do {
            row = Random.nextInt(3)
            col = Random.nextInt(3)
        } while (grid[row][col].isNotEmpty())
        return Pair(row, col)
    }

    //THIS METHOD IS USED SO THAT THE COMPUTER CAN ACTUALLY MAKE A MOVE AND PRESS THE BUTTON
    private fun getButtonForPosition(row: Int, col: Int): Button {
        return when (row) {
            0 -> when (col) {
                0 -> findViewById(R.id.buttonLU)
                1 -> findViewById(R.id.buttonMU)
                else -> findViewById(R.id.buttonRU)
            }
            1 -> when (col) {
                0 -> findViewById(R.id.buttonLM)
                1 -> findViewById(R.id.buttonMM)
                else -> findViewById(R.id.buttonRM)
            }
            else -> when (col) {
                0 -> findViewById(R.id.buttonLD)
                1 -> findViewById(R.id.buttonMD)
                else -> findViewById(R.id.buttonRD)
            }
        }
    }

    private fun checkWin(row: Int, col: Int, player: String): Boolean {

        if (grid[row].count { it == player } == 3) return true


        if ((0 until 3).count { grid[it][col] == player } == 3) return true


        if ((row == col || row + col == 2) && ((0 until 3).count { grid[it][it] == player } == 3 ||
                    (0 until 3).count { grid[it][2 - it] == player } == 3)) return true

        return false
    }

    private fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun resetGame() {
        grid.forEach { it.fill("") }
        player1Turn = true
        moves = 0

        val buttons = arrayOf(
            findViewById<Button>(R.id.buttonLU),
            findViewById<Button>(R.id.buttonLM),
            findViewById<Button>(R.id.buttonLD),
            findViewById<Button>(R.id.buttonMU),
            findViewById<Button>(R.id.buttonMM),
            findViewById<Button>(R.id.buttonMD),
            findViewById<Button>(R.id.buttonRU),
            findViewById<Button>(R.id.buttonRM),
            findViewById<Button>(R.id.buttonRD)
        )
        buttons.forEach { button ->
            button.text = ""
            button.textSize = 24f
            button.setTextColor(resources.getColor(android.R.color.black, null))
        }
    }
}

