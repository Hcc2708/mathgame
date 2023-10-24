package com.manash.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView


class Scoreboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scoreboard)
//        val savedFiles = applicationContext.fileList()
//        savedFiles.reverse()
        val list = mutableListOf<Dataset>()
        val lv = findViewById<ListView>(R.id.list)

        val db = DBHelper(this, null)
        val cursor = db.getName()
        cursor!!.moveToFirst()
        while(cursor.moveToNext()){
            val getNameColumIndex = cursor.getColumnIndex(DBHelper.NAME_COl)
            val getScoreColumIndex = cursor.getColumnIndex(DBHelper.Score)
            if(getNameColumIndex != -1 && getScoreColumIndex != -1)
            {
                list.add(Dataset(cursor.getString(getNameColumIndex), cursor.getString(getScoreColumIndex)))
            }
        }
        cursor.close()
        val adapter = CustomAdapter(this, android.R.layout.simple_list_item_1, list)
        lv.adapter = adapter
        lv.setOnItemClickListener { parent, view, position, id ->
            val intValue = id.toInt()
            val item = lv.getItemAtPosition(intValue).toString()
            val intent = Intent(this, ScoreboardResult::class.java)


            val bundle = Bundle()
            bundle.putString("myKey", item)

            intent.putExtras(bundle)

            startActivity(intent)
            finish()
    }

        val home=findViewById<ImageButton>(R.id.home)
        home.setOnClickListener {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()}


    }
    }

