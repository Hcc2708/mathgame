package com.manash.mathgame


import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.*
import kotlin.random.Random

class Divide : AppCompatActivity() {

    lateinit var textScore : TextView
    lateinit var textLife : TextView

    lateinit var textQuestion : TextView
    lateinit var editTextAnswer : EditText

    lateinit var buttonOk : Button
    lateinit var buttonNext : Button
    lateinit var answerView: TextView

    lateinit var level: TextView
    lateinit var levelView: TextView

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3
    var number1=0
    var number2=0
    var activity=2






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.divide)

        supportActionBar!!.title = "Addition"

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewlife)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.buttonOK)
        buttonNext = findViewById(R.id.buttonNext)
        answerView=findViewById(R.id.answerView)


        level=findViewById(R.id.level)
        levelView=findViewById(R.id.levelView)


        val image=findViewById<ImageView>(R.id.lifeImage)
        gameContinue()

        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()

            if(input == ""){
                Toast.makeText(applicationContext,"Please write an answer or click the next button",Toast.LENGTH_LONG).show()
            }
            else{
                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer){
                    userScore += 10
                    textQuestion.text = "Congratulation! \nYour answer is correct"
                    buttonOk.visibility= View.INVISIBLE
                    textScore.text = userScore.toString()
                    buttonNext.text="NEXT"


                }
                else{
                    textQuestion.text = "Sorry! Your answer is \nwrong"
                    buttonOk.visibility= View.INVISIBLE
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
                    answerView.text="Correct Answer is \n   $number1+$number2=$correctAnswer"
                    textLife.text = userLife.toString()
                    buttonNext.text="NEXT"
                }

            }

        }
        buttonNext.setOnClickListener {
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
            else{

                gameContinue()

            }


        }
    }
    private fun gameContinue(){
        number1 = Random.nextInt(1,100)
        number2 = Random.nextInt(1,10)
        buttonNext.text="SKIP"
        answerView.visibility=View.INVISIBLE
        if(number1%number2==0){
            textQuestion.text = "$number1 / $number2"
            correctAnswer = (number1)/(number2)

        }else{
            gameContinue()
        }


    }
    fun customExitDialog() {
        // creating custom dialog
        val dialog = Dialog(this@Divide)

        // setting content view to dialog
        dialog.setContentView(R.layout.custom_exit_dialog)

        // getting reference of TextView
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