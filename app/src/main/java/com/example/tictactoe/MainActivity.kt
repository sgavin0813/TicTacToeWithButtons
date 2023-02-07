package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    protected var whoseTurn:Int = 0
    protected var count:Int = 0
    protected var gameOver:Boolean = false
    var buttons = arrayOf<Button>()
    lateinit var winner: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttons = arrayOf(
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2) ,
            findViewById<Button>(R.id.btn3) ,
            findViewById<Button>(R.id.btn4) ,
            findViewById<Button>(R.id.btn5) ,
            findViewById<Button>(R.id.btn6) ,
            findViewById<Button>(R.id.btn7) ,
            findViewById<Button>(R.id.btn8) ,
            findViewById<Button>(R.id.btn9)
        )
        winner = findViewById<TextView>(R.id.winner)
    }

    fun whenClicked(viewCurrent : View) {
        //the reference of button we click is saved in current button
        var currentButton = viewCurrent
        if (whoseTurn==0 && !gameOver){
            if ((currentButton as Button).text == ""){
            (currentButton as Button).text = "X"
            //count is same as number of boxes filled
            whoseTurn++
            count ++
            }
        }
        else if(whoseTurn==1){
            if ((currentButton as Button).text == ""){
            (currentButton as Button).text = "O"
            count++
            whoseTurn--
            }
        }
        //we can have winner at atleast 5th term
        if(count in 5..9){
            for (i in 0..5 step 2){
                if ((buttons[i].getText().toString()) ==(buttons[i+1].getText().toString())
                    && ((buttons[i+1].getText().toString())==(buttons[i+2].getText().toString()))) {
                    winner.setText("${buttons[i].getText().toString()} is Winner")
                    gameOver = true
                    break
                }
            }
            for (i in 0..2 step 1){
                if ((buttons[i].getText().toString()) ==(buttons[i+3].getText().toString())
                    && ((buttons[i+3].getText().toString())==(buttons[i+6].getText().toString()))) {
                    winner.setText("${buttons[i].getText().toString()} is Winner")
                    gameOver = true
                    break
                }
            }
            if ((buttons[0].getText().toString()) ==(buttons[4].getText().toString())
                && ((buttons[4].getText().toString())==(buttons[8].getText().toString()))) {
                winner.setText("${buttons[0].getText().toString()} is Winner")
                gameOver = true
            }
            if ((buttons[2].getText().toString()) ==(buttons[4].getText().toString())
                && ((buttons[4].getText().toString())==(buttons[6].getText().toString()))) {
                winner.setText("${buttons[2].getText().toString()} is Winner")
                gameOver = true
            }


        }


    }


}
