package com.example.myapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myapp.Impl.AssignmnetRepositoryImp
import com.example.myapp.Impl.HomeImpl
import com.example.myapp.Interface.HomePresenter
import com.example.myapp.Interface.Homeview

class MainActivity : AppCompatActivity(), Homeview {

    private lateinit var homePresenter: HomePresenter
    internal lateinit var label: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homePresenter = HomeImpl(this, AssignmnetRepositoryImp())
        label = findViewById(R.id.launchText)
        homePresenter.findAssignment()
    }

    override fun response(resp: String) {
        label.setText(resp)
        print(resp)
    }
}
