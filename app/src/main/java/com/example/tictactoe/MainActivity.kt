package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger.global



class MainActivity : AppCompatActivity() {
    private var whoseTurn:Int = 0
    private var count:Int = 0
    private var gameOver:Boolean = false
    var buttons = arrayOf<Button>()
    private lateinit var winner: TextView
    private lateinit var resetButton: Button
    private lateinit var turnBanner: TextView
    private var player:String ="X"
    private var savedStringForX:String = ""
    private var savedStringForY:String = ""
    private lateinit var arrayForX:IntArray
    private lateinit var arrayForY:IntArray
    private lateinit var  sharedPreferences:SharedPreferences
    private var testString:String = ""
    private var gameStarted=false
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
        turnBanner = findViewById<Button>(R.id.turnBanner)
        resetButton.setOnClickListener {
            whenClickedReset()
        }
        navBar.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.homeButton -> Toast.makeText(applicationContext,testString, Toast.LENGTH_SHORT).show()
                R.id.rest -> whenClickedReset()
            }
            true
        }
        sharedPreferences = getSharedPreferences("mySharedPreferences" , Context.MODE_PRIVATE )
    }

    private fun whenClickedReset(){
        resetButton.visibility = View.INVISIBLE
        for (i in 0 until 9) {
            buttons[i].text = ""
        }
        whoseTurn = 0
        gameStarted = false
        gameOver = false
        winner.text = "No winner yet"
        count = 0
    }
    fun whenClicked(viewCurrent: View) {
        gameStarted = true
        var index = buttons.indexOf(viewCurrent)
        //the reference of button we click is saved in current button
        val currentButton = viewCurrent
        //exit if game over
        if(gameOver){
            return
        }
        if (whoseTurn == 0) {
            turnBanner.text ="O's Turn"
            if ((currentButton as Button).text == "") {
                player = "X"
                currentButton.text = player
                //count is same as number of boxes filled
                whoseTurn++
                count++
                //this is used for saved preferences
                //all the index that have x are saved
                savedStringForX = "$savedStringForX$index-"
            }
        } else if (whoseTurn == 1) {
            turnBanner.text ="X's Turn"
            if ((currentButton as Button).text == "") {
                player = "O"
                currentButton.text = player
                count++
                whoseTurn--
                savedStringForY= "$savedStringForY$index-"
            }
        }
        //we can have winner at at least 5th term
        if (count in 5..9) {
            if (count == 9 ) {
                resetButton.visibility = View.VISIBLE
            }

            //for row

            for (i in 0..8 step 3) {
                if ((buttons[i].text.toString() == player) && (buttons[i + 1].text.toString() == player) && (buttons[i + 2].text.toString() == player)) {
                    winner.text = "$player is Winner"
                    gameOver = true
                    resetButton.visibility = View.VISIBLE
                    break
                }
            }
            //for column
            for (i in 0..2) {
                if ((buttons[i].text.toString() == player) && (buttons[i + 3].text.toString() == player) && (buttons[i + 6].text.toString() == player)) {
                    winner.text = "$player is Winner"
                    gameOver = true
                    resetButton.visibility = View.VISIBLE
                    break
                }
            }

            if ((buttons[0].text.toString()) == (buttons[4].text.toString())
                && ((buttons[4].text.toString()) == (buttons[8].text.toString()))
            ) {
                winner.text = "${buttons[0].text} is Winner"
                resetButton.visibility = View.VISIBLE
                gameOver = true
            }
            if ((buttons[2].text.toString()) == (buttons[4].text.toString())
                && ((buttons[4].text.toString()) == (buttons[6].text.toString()))
            ) {
                winner.text = "${buttons[2].text} is Winner"
                resetButton.visibility = View.VISIBLE
                gameOver = true
            }

        }
    }



    fun loadGame(view: View) {
        whenClickedReset()
        val gameSaved = sharedPreferences.getBoolean("gameSaved",false)
        this.whoseTurn = sharedPreferences.getInt("whoseTurn",0)
        this.count = sharedPreferences.getInt("count",0)
            if (gameSaved){
                if (whoseTurn==1){
                    turnBanner.text ="O's Turn"
                }
                else{
                    turnBanner.text ="X's Turn"
                }
                val stringX = sharedPreferences.getString("valueOfX"," 0-")?.trimStart()?.removeSuffix("-")?.split("-")
                val stringY = sharedPreferences.getString("valueOfY"," 0-")?.trimStart()?.removeSuffix("-")?.split("-")
                val arrayX = stringX?.map { it.toInt() }?.toIntArray()
                val arrayY = stringY?.map { it.toInt() }?.toIntArray()

            arrayX?.sort()
            if (arrayX !=null && arrayY !=null){
                for (i in arrayX)
                {
                    buttons[i].text = "x"

                }
                for (i in arrayY)
                {
                    buttons[i].text = "O"

                }
            }
        }
        else{
            Toast.makeText(this,"No Data Found!",Toast.LENGTH_SHORT).show()
        }

    }


    fun saveGame(view: View) {
        if (!gameStarted){
            Toast.makeText(this,"Nothing To Store",Toast.LENGTH_SHORT).show()
        }
        else{
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("valueOfX", savedStringForX)
            editor.putString("valueOfY", savedStringForY)
            editor.putBoolean("gameSaved",gameStarted)
            editor.putInt("whoseTurn",whoseTurn)
            editor.putInt("count",count)
            editor.apply()
            turnBanner.text = "X's Turn"
            whenClickedReset()
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
        }
    }

}



