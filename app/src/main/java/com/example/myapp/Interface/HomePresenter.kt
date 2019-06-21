package com.example.myapp.Interface

import android.content.Context
import com.example.myapp.Impl.Assignment

interface HomePresenter {
    fun findAssignment(context: Context)
    fun insertAssignmentToDB(context: Context, assignment: Assignment)
}