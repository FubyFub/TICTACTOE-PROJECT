package com.example.tictactoe
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.os.Handler
import android.os.Looper


class PVPActivity : Activity() {

    private lateinit var player1Name: String
    private lateinit var player2Name: String

    //WE USE THE GRID FOR THE GAME ITSELF, WE PUT EACH BUTTON INTO A POSITION ON THE GRID
    private val grid = Array(3) { Array(3) { "" } }

    //OUR VARIABLES ARE STORED HERE
    private var player1Turn = true
    private var moves = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pvp)

        //TO HOLD THE NAMES OF BOTH PLAYERS
        player1Name = intent.getStringExtra("PLAYER1_NAME") ?: "Player 1"
        player2Name = intent.getStringExtra("PLAYER2_NAME") ?: "Player 2"


        //WE FIND EACH BUTTON FROM THE XML FILE
        val buttonLU: Button = findViewById(R.id.buttonLU)
        val buttonLM: Button = findViewById(R.id.buttonLM)
        val buttonLD: Button = findViewById(R.id.buttonLD)
        val buttonMU: Button = findViewById(R.id.buttonMU)
        val buttonMM: Button = findViewById(R.id.buttonMM)
        val buttonMD: Button = findViewById(R.id.buttonMD)
        val buttonRU: Button = findViewById(R.id.buttonRU)
        val buttonRM: Button = findViewById(R.id.buttonRM)
        val buttonRD: Button = findViewById(R.id.buttonRD)


        //ASSIGNING EACH BUTTON A POSITION ON THE GRID
        buttonLU.setOnClickListener { onButtonClick(0, 0, buttonLU) }
        buttonLM.setOnClickListener { onButtonClick(1, 0, buttonLM) }
        buttonLD.setOnClickListener { onButtonClick(2, 0, buttonLD) }
        buttonMU.setOnClickListener { onButtonClick(0, 1, buttonMU) }
        buttonMM.setOnClickListener { onButtonClick(1, 1, buttonMM) }
        buttonMD.setOnClickListener { onButtonClick(2, 1, buttonMD) }
        buttonRU.setOnClickListener { onButtonClick(0, 2, buttonRU) }
        buttonRM.setOnClickListener { onButtonClick(1, 2, buttonRM) }
        buttonRD.setOnClickListener { onButtonClick(2, 2, buttonRD) }

        //PARSING IN PLAYER NAMES AND DISPLAYING THEM
        val player1TextView = findViewById<TextView>(R.id.player1TextView)
        val player2TextView = findViewById<TextView>(R.id.player2TextView)
        player1TextView.text = player1Name
        player2TextView.text = player2Name

    }

    //METHOD TO INVOKE AN ACTION WHEN A BUTTON IS PRESSED
    private fun onButtonClick(row: Int, col: Int, button: Button) {
        //THIS IS USED SO THAT THE PLAYERS COULDNT CLICK ON THE GRID TO MAKE A MOVE THAT HAS BEEN ALREADY DONE
        if (grid[row][col].isNotEmpty()) return

        //WE USE THIS TO DISPLAY THE CORRECT PLAYER NAME AT THE END OF THE GAME
        val currentPlayerName = if (player1Turn) player1Name else player2Name
        //USING THIS TO FILL OUT THE TIC TAC TOE GRID
        val currentPlayer = if (player1Turn) "X" else "O"
        grid[row][col] = currentPlayer
        //CHANGING TEXT TO X OR 0 DEPENDING ON THE PLAYER AND THE TEXT SIZE SO THAT THEY ARE BIGGER
        button.text = currentPlayer
        button.textSize = 52f

        //CHANGING THE COLOR DEPENDING ON THE PLAYER
        val color = if (player1Turn) android.R.color.holo_red_light else android.R.color.holo_blue_light
        button.setTextColor(resources.getColor(color, null))

        //INVOKING THE CHECKWIN METHOD THAT SEES IF SOMEONE WON OR NOT
        if (checkWin(row, col, currentPlayer)) {
            //DISPLAYING THE MESSAGE FOR THE WINNING PLAYER
            displayMessage("Player $currentPlayerName wins!")
            resetGame()
        } else {
            //COUNTING MOVES FOR THE DRAW
            moves++
            if (moves == 9) {
                displayMessage("Draw!")
                resetGame()
            } else {
                //CHANGING PLAYERS TURNS
                player1Turn = !player1Turn
            }
        }
    }

    private fun checkWin(row: Int, col: Int, player: String): Boolean {

        //IF SOMEONE HAS A 3 OCCUPIED SPACES IN THE ROW THE MOVE WAS MADE THEN THAT PLAYER HAS WON
        if (grid[row].count { it == player } == 3) return true

        //IF SOMEONE HAS A 3 OCCUPIED SPACES IN THE COLUMN THE MOVE WAS MADE THEN THAT PLAYER HAS WON
        if ((0 until 3).count { grid[it][col] == player } == 3) return true

        //THIS IS USED TO CHECK FOR DIAGONAL WIN, IF THE COUNT OF THE SAME SYMBOL ON THE SAME DIAGONAL IS 3 THEN THAT PLAYER WINS
        if ((row == col || row + col == 2) && ((0 until 3).count { grid[it][it] == player } == 3 ||
                    (0 until 3).count { grid[it][2 - it] == player } == 3)) return true


        //IF NO ONE WON YET WE RETURN FALSE TO CONTINUE THE GAME
        return false
    }

    //USE THIS TO ACTUALLY DISPLAY A WINNING MESSAGE
    private fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun resetGame() {

        //RESETTING THE GRRID, INICIALISING THE FIRST PLAYER MOVE, RESETING THE MOVE COUNT
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
        //DELETING THE X OR 0 ON THE GRID
        buttons.forEach { button ->
            button.text = ""
            button.textSize = 24f
            button.setTextColor(resources.getColor(android.R.color.black, null))
        }

    }
}
