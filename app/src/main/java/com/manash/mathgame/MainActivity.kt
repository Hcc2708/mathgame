package com.manash.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.manash.mathgame.fragment.Fragment1


class MainActivity : AppCompatActivity() {
    lateinit var addition : ImageButton

        lateinit var subtraction : ImageButton
    lateinit var multi : ImageButton
    lateinit var div : ImageButton
    lateinit var table: ImageButton
    lateinit var scoreboard: ImageButton





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.buttonAdd)
        subtraction = findViewById(R.id.buttonSub)
        multi = findViewById(R.id.buttonMulti)
        div= findViewById(R.id.buttondiv)
        table=findViewById(R.id.table)
        scoreboard=findViewById(R.id.scoreboard)


        //---------------------------------------------------



scoreboard.setOnClickListener {

    val i =Intent(this,Scoreboard::class.java)
    startActivity(i)
}
        //-------------------------------------



        //--------------------------------------------------------------
        addition.setOnClickListener {

            val intent = Intent(this@MainActivity,UserName::class.java)

            intent.putExtra("value",addition.transitionName)
           // finish()
            startActivity(intent)

        }
        subtraction.setOnClickListener {

            val intent = Intent(this@MainActivity,UserName::class.java)

            intent.putExtra("value",subtraction.transitionName)
           // finish()
            startActivity(intent)


        }
        multi.setOnClickListener {

            var intent = Intent(this@MainActivity,UserName::class.java)

            intent.putExtra("value",multi.transitionName)
            startActivity(intent)
           // finish()

        }
        div.setOnClickListener {
            var intent = Intent(this@MainActivity,UserName::class.java)

            intent.putExtra("value",div.transitionName)
            startActivity(intent)
        //finish()
        }
        table.setOnClickListener {
            val intent = Intent(this@MainActivity,MultiTable::class.java)
            startActivity(intent)
        }





    }

}