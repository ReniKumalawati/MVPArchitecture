package com.example.myapp.Impl

import android.content.Context
import android.util.Log
import com.example.myapp.Interface.AssignmentRepository
import com.example.myapp.Interface.HomePresenter
import com.example.myapp.Interface.Homeview
import com.example.myapp.Responses.InsertResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.Observer

class HomeImpl: HomePresenter {
    var homeview: Homeview
    var repository: AssignmentRepository
    var compositeDisposable: CompositeDisposable

    constructor(homeview: Homeview, assignmnetRepositoryImp: AssignmnetRepositoryImp) {
        this.homeview = homeview
        this.repository = assignmnetRepositoryImp
        this.compositeDisposable = CompositeDisposable();
    }

    override fun insertAssignmentToDB(context: Context, assignment: Assignment) {
        compositeDisposable.addAll(repository.addAssignmentToDB(context, assignment).subscribe({showToast(it)}))

    }
    override fun findAssignment(context: Context) {
        compositeDisposable.add(repository.loadAssignment(context).subscribe({ sendAssigmentsToView(it) }))

    }

    private fun sendAssigmentsToView(assignments: ArrayList<Assignment>) {
        homeview.response(assignments)
    }

    private fun showToast(response: InsertResponse) {
        homeview.showToast(response)
        homeview.reloadData()
    }
}