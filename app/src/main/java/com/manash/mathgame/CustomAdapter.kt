package com.manash.mathgame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(var mCtx: Context, var resources:Int,
                    var items:MutableList<Dataset>):
    ArrayAdapter<Dataset>(mCtx, resources, items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)
        val textView:TextView = view.findViewById(android.R.id.text1)

        val mItem:Dataset = items.get(position)
        textView.text = mItem.name + "  " + mItem.score
        return view
    }

}