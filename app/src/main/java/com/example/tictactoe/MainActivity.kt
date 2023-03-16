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



class MainActivity : AppCompatActivity() {
    private var whoseTurn:Int = 0
    private var count:Int = 0
    private var gameOver:Boolean = false
    var buttons = arrayOf<Button>()
    private lateinit var winner: TextView
    private lateinit var resetButton: Button
    private var player:String ="X"
    private var savedStringForX:String = ""
    private var savedStringForY:String = ""
    private lateinit var arrayForX:IntArray
   /* private val sharedPreferences = getSharedPreferences("mySharedPreferences" , Context.MODE_PRIVATE )
*/
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
        navBar.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.homeButton -> Toast.makeText(applicationContext,arrayForX.contentToString(), Toast.LENGTH_SHORT).show()

            }
            true
        }

    }

    fun whenClicked(viewCurrent: View) {
        var index = buttons.indexOf(viewCurrent)
        //the reference of button we click is saved in current button
        val currentButton = viewCurrent
        //exit if game over
        if(gameOver){
            return
        }
        if (whoseTurn == 0) {
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

    fun resetFunction(view: View) {
        for (i in 0 until 9) {
            buttons[i].text = ""
        }
        gameOver = false
        winner.text = "No winner yet"
        (view as Button) .visibility = View.INVISIBLE
        whoseTurn = 0
        count = 0
        }

    fun loadGame(view: View) {
      /*  val stringX: String? = sharedPreferences.getString("valueOfX"," 0-")
        val stringY: String? = sharedPreferences.getString("valueOfY"," 0-")

        val stringArrayForX = stringX!!.removeSuffix("-").trimStart().split("-")
        arrayForX = stringArrayForX.map { it.toInt() }.toIntArray()
        val stringArrayForY = stringY!!.removeSuffix("-").trimStart().split("-")
        arrayForX = stringArrayForY.map { it.toInt() }.toIntArray()*/

    }

    fun saveGame(view: View) {
        /*val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("valueOfX", savedStringForX)
        editor.putString("valueOfY", savedStringForY)*/
    }

}



