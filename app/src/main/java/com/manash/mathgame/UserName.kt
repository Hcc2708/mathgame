package com.manash.mathgame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import java.io.FileOutputStream
import java.io.IOException

class UserName : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var btnPlay: ImageButton


   // lateinit var data:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_name)

        username=findViewById(R.id.UserName)
        btnPlay=findViewById(R.id.ButtonPlay)

        btnPlay.setOnClickListener {

            if (username.text.isEmpty()){
                Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show()
            }else{

//                val i=Intent(this,ResultActivity::class.java)
//                i.putExtra("name",username.text)

                val input= intent.getStringExtra("value").toString()

                if (input=="add"){
                    val i = Intent(this,Add::class.java)
                    i.putExtra("name",username.text.toString())
                    startActivity(i)
                    finish()


                }else if (input=="sub"){
                    val i2 = Intent(this,Minus::class.java)
                    i2.putExtra("name",username.text.toString())

                    startActivity(i2)
                    finish()
                }else if (input=="multi") {
                    val i3 = Intent(this, Multi::class.java)
                    i3.putExtra("name",username.text.toString())
                    startActivity(i3)
                    finish()
                }else if (input=="div") {
                    val i4 = Intent(this, Divide::class.java)
                   i4.putExtra("name",username.text.toString())
                    startActivity(i4)
                    finish()
                }
            }


//-----------------------------
//                val file:String = username.text.toString()
//
//                        try{
//                            val fout = openFileOutput(file, MODE_APPEND)
//
//                            fout.close()
//                            Toast.makeText(this,"file saved",Toast.LENGTH_LONG).show()
//                        }
//                        catch (to:IOException){
//
//                        }
//
//
//
//
//                }


           }
                //-----------------



            }



}