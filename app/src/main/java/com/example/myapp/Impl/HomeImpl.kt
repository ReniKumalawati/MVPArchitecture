package com.example.myapp.Impl

import android.util.Log
import com.example.myapp.Interface.AssignmentRepository
import com.example.myapp.Interface.HomePresenter
import com.example.myapp.Interface.Homeview

class HomeImpl: HomePresenter {
    lateinit var homeview: Homeview
    lateinit var repository: AssignmentRepository

    constructor(homeview: Homeview, assignmnetRepositoryImp: AssignmnetRepositoryImp) {
        this.homeview = homeview
        this.repository = assignmnetRepositoryImp
    }
    override fun findAssignment() {
        var assignment = repository.loadAssignment()
       homeview.response(assignment.title)
    }
}