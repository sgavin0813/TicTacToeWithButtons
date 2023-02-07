package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private var whoseTurn:Int = 0
    private var count:Int = 0
    private var gameOver:Boolean = false
    var buttons = arrayOf<Button>()
    private lateinit var winner: TextView
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttons = arrayOf(
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )
        winner = findViewById<TextView>(R.id.winner)
        resetButton = findViewById<Button>(R.id.reset)
    }
    fun resetFunction(viewCurrent : View){
        for (i in 0 until 9) {
            buttons[i].text = ""
        }
        gameOver = false
        winner.text = "No winner yet"
        (viewCurrent as Button).visibility = View.INVISIBLE
        whoseTurn = 0
        count = 0
    }

    fun whenClicked(viewCurrent : View) {
        //the reference of button we click is saved in current button
        var currentButton = viewCurrent
        if (whoseTurn==0 && !gameOver){
            if ((currentButton as Button).text == ""){
            currentButton.text = "X"
            //count is same as number of boxes filled
            whoseTurn++
            count ++
            }
        }
        else if(whoseTurn==1){
            if ((currentButton as Button).text == ""){
            currentButton.text = "O"
            count++
            whoseTurn--
            }
        }
        //we can have winner at at least 5th term
        if(count in 5..9){
            if(count == 9){
                resetButton.visibility = View.VISIBLE
            }
            for (i in 0..8 step 3){
                if ((buttons[i].text.toString()) ==(buttons[i+1].text.toString())
                    && ((buttons[i+1].text.toString())==(buttons[i+2].text.toString()))) {
                    winner.text = "${buttons[i].text} is Winner"
                    gameOver = true
                    resetButton.visibility = View.VISIBLE
                    break
                }
            }
            
            for (i in 0..2 step 1){
                if ((buttons[i].text.toString()) ==(buttons[i+3].text.toString())
                    && ((buttons[i+3].text.toString())==(buttons[i+6].text.toString()))) {
                    winner.text = "${buttons[i].text} is Winner"
                    gameOver = true
                    resetButton.visibility = View.VISIBLE
                    break
                }
            }
            if ((buttons[0].text.toString()) ==(buttons[4].text.toString())
                && ((buttons[4].text.toString())==(buttons[8].text.toString()))) {
                winner.text = "${buttons[0].text} is Winner"
                resetButton.visibility = View.VISIBLE
                gameOver = true
            }
            if ((buttons[2].text.toString()) ==(buttons[4].text.toString())
                && ((buttons[4].text.toString())==(buttons[6].text.toString()))) {
                winner.text = "${buttons[2].text} is Winner"
                resetButton.visibility = View.VISIBLE
                gameOver = true
            }

        }

    }



}
