package com.manash.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import java.io.*


class ResultActivity : AppCompatActivity() {

    lateinit var result : TextView
    lateinit var playAgain : Button
    lateinit var exit : Button
    lateinit var tv: TextView
    lateinit var tvname:TextView
    lateinit var data:String
    lateinit var home:ImageButton
    lateinit var scorebrd: ImageButton
    lateinit var rating: RatingBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        tv=findViewById(R.id.textView5)
        result = findViewById(R.id.textViewResult)
        playAgain = findViewById(R.id.buttonAgain)
        exit = findViewById(R.id.buttonExit)
        tvname=findViewById(R.id.textViewname)
        home=findViewById(R.id.homebtn)
        scorebrd=findViewById(R.id.scorebrd)
        rating=findViewById(R.id.ratingbar)


        val name=intent.getStringExtra("name")
        val score = intent.getIntExtra("score",0)
        val life=intent.getIntExtra("life",0)


        if(score==0){
            tv.setText("Opps..")
            rating.rating = 0F
        }else{
            tv.setText("Congratulations")
            rating.rating =life.toFloat()
        }
        tvname.setText(name)
            result.text ="Your score is : " + score
     //adding------------------------------DELETE-----------------
//        val del = File(applicationContext.filesDir, name)
//
//            if (del.exists()) {
//                del.delete()
//   //------------------------------------add----------------------------
//                val file: String = name.toString()
//
//                data = score.toString()
//
//                Toast.makeText(this, "Game over", Toast.LENGTH_LONG).show()
//
//                try {
//                    val fout = openFileOutput(file, MODE_APPEND)
//                    fout.write(data.toByteArray())
//                    fout.close()
//
//                } catch (to: IOException) {
//
//                }
//            }else{
//                val file:String = name.toString()
//
//                data= score.toString()
//
//
//                try{
//                    val fout = openFileOutput(file, MODE_APPEND)
//                    fout.write(data.toByteArray())
//                    fout.close()
//
//                }
//                catch (to: IOException){
//
//             }
//            }

// SQL Database
        val db = DBHelper(this, null)
        db.addName(name.toString(), score.toString())
//-------------------------------------------------------

        playAgain.setOnClickListener {
            val act=intent.getIntExtra("activity",0)
            if (act==1){

                val intent = Intent(this@ResultActivity,Add::class.java)
                intent.putExtra("name",name)
                startActivity(intent)

                finish()
            }else if (act==2){
                val intent = Intent(this@ResultActivity,Divide::class.java)
                intent.putExtra("name",name)
                startActivity(intent)
                finish()
            }else if (act==3) {
                val intent = Intent(this@ResultActivity, Minus::class.java)
                intent.putExtra("name",name)
                startActivity(intent)
                finish()
            }else if (act==4) {
                val intent = Intent(this@ResultActivity, Multi::class.java)
                intent.putExtra("name",name)
                startActivity(intent)
                finish()
            }

        }


        exit.setOnClickListener {

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }
        home.setOnClickListener {
            val intent = Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        scorebrd.setOnClickListener {
            val intent = Intent(this@ResultActivity,Scoreboard::class.java)
            startActivity(intent)
            finish()
    }

}}