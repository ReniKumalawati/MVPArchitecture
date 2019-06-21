package com.example.myapp.Interface

import android.content.Context
import com.example.myapp.Impl.Assignment
import com.example.myapp.Responses.InsertResponse
import io.reactivex.Observable

interface AssignmentRepository {

    fun loadAssignment(context: Context): Observable<ArrayList<Assignment>>

    fun addAssignmentToDB(context: Context, assignment: Assignment): Observable<InsertResponse>
}