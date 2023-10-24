package com.manash.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.File

class  ScoreboardResult : AppCompatActivity() {
    lateinit var tv:TextView
    lateinit var tvname: TextView
    lateinit var delete :ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scoreboard_result)
        supportActionBar!!.title = "Scoreboard"

        tv = findViewById(R.id.tvresult)
        tvname=findViewById(R.id.tvname)
        val bundle = intent.extras
        val myVariable = bundle?.getString("myKey")


        val file = myVariable
        val fin = openFileInput(file)

        var c: Int
        var temp = ""
        while (fin.read().also { c = it } != -1) {
            temp += c.toChar().toString()
        }
        val arr = temp.split(",")
        tvname.text=file.toString()
        tv.text = "Score: ".toString()+arr[0]
//        tv2.text =arr[1]
//        tv3.text=arr[2]
//        tv4.text=arr[3]
//        tv5.text=arr[4]
//        tv6.text=arr[5]
//        tv7.text=arr[6]

        // val context = applicationContext

        delete = findViewById(R.id.deletebtn)
        val del = File(applicationContext.filesDir, myVariable)
        //Toast.makeText(this, myVariable, Toast.LENGTH_SHORT).show()
        //-------------------------------deletebtn--------------------
        delete.setOnClickListener {
            if (del.exists()) {
//-----------------------------------------------------------------------------------------------
                val builder= AlertDialog.Builder(this) // builder: local variable

                builder.setTitle("Delete") //for title
                    .setMessage("Do you want to remove $myVariable ?")
                    //.setCancelable(true)
                    .setIcon(R.drawable.ic_delete)
                builder.setPositiveButton("Yes"){
                        dialogInterface, which->
                    del.delete()

                    del.delete()
                    val intent = Intent(this, Scoreboard::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Removed $myVariable successfully", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No"){dialog, which->

                }

                val ad: AlertDialog =builder.create()
                ad.show()
            }
        //------------------------------------
//        delete.setOnClickListener {
//            if (del.exists()) {
//                  del.delete()
//                val intent = Intent(this, Scoreboard::class.java)
//                startActivity(intent)
//                finish()
//            }
        }
    }}