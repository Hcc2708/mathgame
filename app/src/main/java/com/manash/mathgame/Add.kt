package com.manash.mathgame

import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast



import kotlin.random.Random

class Add : AppCompatActivity() {

    lateinit var textScore : TextView
    lateinit var textLife : TextView


    lateinit var level: TextView
    lateinit var levelView: TextView

    lateinit var textQuestion : TextView
    lateinit var editTextAnswer : EditText

    lateinit var buttonOk : Button
    lateinit var buttonNext : Button
    lateinit var answerView: TextView

   // lateinit var skipNumber: TextView

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3
    var number1=0
    var number2=0
    var activity=1

  //  var skip=5





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add)

        supportActionBar!!.title = "Addition"

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewlife)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.buttonOK)
        buttonNext = findViewById(R.id.buttonNext)
        answerView=findViewById(R.id.answerView)

       // skipNumber=findViewById(R.id.skipNumber)

        //-----------animation-------------------
        val image=findViewById<ImageView>(R.id.lifeImage)
        val scoreIncrease=findViewById<TextView>(R.id.textViewScore)
        //-------------------------------------------


        level=findViewById(R.id.level)
        levelView=findViewById(R.id.levelView)



        gameContinue()

        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()

            if(input == ""){
                Toast.makeText(applicationContext,"Please write an answer and click the submit button",Toast.LENGTH_LONG).show()
            }
            else{

             //   skipNumber.visibility=View.INVISIBLE
                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer){
                    userScore += 10

//                    if((userScore==50 || userScore==100 || userScore==150) && skip<5){
//
//                        skip++
//                        skipNumber.text=skip.toString()
//                        if(buttonNext.text=="SKIP"){
//                            skipNumber.visibility=View.VISIBLE
//                        }
//                    }

                                      textQuestion.text = "Congratulation! \nYour answer is correct"
                    buttonOk.visibility= View.INVISIBLE
                    textScore.text = userScore.toString()

                    buttonNext.visibility=View.VISIBLE
                    buttonNext.text="NEXT"


                }
                else{
                    textQuestion.text = "Sorry! Your answer is \nwrong"

                    buttonOk.visibility= View.INVISIBLE
                //    skipNumber.visibility=View.INVISIBLE

                    textLife.text = userLife.toString()
                    userLife--
                    //------------animation---------------------
//                    val animZoomIn = AnimationUtils.loadAnimation(this,
//                        R.anim.zoom_in)
//                    // assigning that animation to
//                    // the image and start animation
//                    image.startAnimation(animZoomIn)


                    // Create the scale animation
                    val scaleAnimation = ScaleAnimation(
                        1f, 1.6f, // Start and end scale X
                        1f, 1.6f, // Start and end scale Y
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X
                        Animation.RELATIVE_TO_SELF, 0.5f // Pivot Y
                    ).apply {
                        duration = 500 // Animation duration in milliseconds
                        repeatCount = Animation.RELATIVE_TO_SELF // Repeat the animation infinitely
                        repeatMode = Animation.REVERSE // Reverse the animation when it repeats
                    }

                    // Start the animation
                    image.startAnimation(scaleAnimation)





                    //-----------------------------

                    answerView.visibility=View.VISIBLE
                    answerView.text="Correct Answer is \n$number1+$number2=$correctAnswer"
                    textLife.text = userLife.toString()
                    buttonNext.visibility=View.VISIBLE
                        //        skipNumber.visibility=View.INVISIBLE
                    buttonNext.text="NEXT"
                }

            }

        }


        buttonNext.setOnClickListener {

//            if(buttonNext.text=="SKIP"){
//                skipNumber.visibility=View.VISIBLE
//            }
//            if(skip>1 && buttonNext.text=="SKIP"){
//            skip--
//                buttonNext.visibility=View.VISIBLE
//                skipNumber.visibility=View.VISIBLE
//            skipNumber.text=skip.toString()}
//            else if (skip<=1){
//                skipNumber.visibility=View.INVISIBLE
//                buttonNext.visibility=View.INVISIBLE
//                skip=0
//            }

            answerView.text=""


            gameContinue()

            editTextAnswer.setText("")
            buttonOk.visibility= View.VISIBLE

            val input = editTextAnswer.text.toString()
            if (input == "" ){


            }
            if (userLife == 0){

                Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_LONG).show()
                val ip=intent.getStringExtra("name")


                val i = Intent(this,ResultActivity::class.java)

                i.putExtra("score",userScore)
                i.putExtra("name",ip)
                i.putExtra("activity",activity)
                i.putExtra("life",userLife)
               startActivity(i)
               finish()

            }
            else

                gameContinue()

            }


        }

    fun gameContinue(){
        if(userScore<100){
            levelView.text="1"
         number1 = Random.nextInt(0,10)
         number2 = Random.nextInt(0,10)}
        else if (userScore>=100 && userScore<200){
            levelView.text="2"
            level.setTextColor(Color.YELLOW)
            number1= Random.nextInt(0,10)
            number2=Random.nextInt(0,100)
        }else if(userScore>=200 && userScore<300)
        {            levelView.text="3"
            level.setTextColor(Color.argb(255,127,80,30))
            number1= Random.nextInt(0,100)
            number2=Random.nextInt(0,100)
        }else if(userScore>=300 && userScore<400)
        {            levelView.text="4"
            level.setTextColor(Color.BLUE)
            number1= Random.nextInt(0,100)
            number2=Random.nextInt(0,1000)
        }else{
            levelView.text="5"
            level.setTextColor(Color.RED)
            number1= Random.nextInt(0,100)
            number2=Random.nextInt(0,1000)
        }

        answerView.visibility=View.INVISIBLE

        buttonNext.text="SKIP"
//        if (skip>0) {
//            skipNumber.visibility = View.VISIBLE
//        }
        textQuestion.text = "$number1 + $number2"
        correctAnswer = number1 + number2


    }
    fun customExitDialog() {

        val dialog = Dialog(this@Add)


        dialog.setContentView(R.layout.custom_exit_dialog)
        val dialogButtonYes = dialog.findViewById(R.id.textViewYes) as TextView
        val dialogButtonNo = dialog.findViewById(R.id.textViewNo) as TextView

        // click listener for No
        dialogButtonNo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //dismiss the dialog
                dialog.dismiss()
            }
        })

        // click listener for Yes
        dialogButtonYes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // dismiss the dialog
                // and exit the exit
                dialog.dismiss()

                val ip=intent.getStringExtra("name")
                val i=Intent(applicationContext,ResultActivity::class.java)


                i.putExtra("score",userScore)
                i.putExtra("name",ip)
                i.putExtra("activity",activity)
                i.putExtra("life",userLife)
                startActivity(i)
                finish()
            }
        })

        // show the exit dialog
        dialog.show()
    }

    override fun onBackPressed() {
        // calling the function
        customExitDialog()
    }




}