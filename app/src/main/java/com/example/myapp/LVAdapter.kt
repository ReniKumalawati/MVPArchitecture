package com.example.myapp

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import com.example.myapp.Impl.Assignment
import kotlinx.android.synthetic.main.row_lv.view.*

class LVAdapter(private val context: Activity, private val datasource: ArrayList<Assignment>) : BaseAdapter() {

    private val inflate: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val row = inflate.inflate(R.layout.row_lv, parent, false)
        row.title.setText(datasource[position].title)
        when(datasource[position].severity) {
            "low" -> row.imageView.setImageResource(R.drawable.green)
            "medium" -> row.imageView.setImageResource(R.drawable.orange)
            else -> row.imageView.setImageResource(R.drawable.red)
        }
        return row
    }

    override fun getItem(position: Int): Any {
        return datasource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return datasource.size
    }
}

