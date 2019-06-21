package com.example.myapp.Impl

import android.content.Context
import android.util.Log
import com.example.myapp.DB.DBOpenHelper
import com.example.myapp.Interface.AssignmentRepository
import com.example.myapp.Responses.InsertResponse
import io.reactivex.Observable


class AssignmnetRepositoryImp: AssignmentRepository {
    override fun addAssignmentToDB(context: Context, assignment: Assignment): Observable<InsertResponse> {
        val dbHandler = DBOpenHelper(context, null)
        dbHandler.addAssignmentToDB(assignment)
        val response = InsertResponse()
        response.message = "data successfully inserted"
        response.status = 200
        return Observable.just(response)
    }

    override fun loadAssignment(context: Context): Observable<ArrayList<Assignment>> {
        var listAssignment = ArrayList<Assignment>()
        val dbHandler = DBOpenHelper(context, null)
        dbHandler.findAllAssignment().subscribe({
            listAssignment = it
        })
        return Observable.just(listAssignment)
 }
}