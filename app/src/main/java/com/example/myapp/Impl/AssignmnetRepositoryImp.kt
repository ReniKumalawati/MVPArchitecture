package com.example.myapp.Impl

import com.example.myapp.Interface.AssignmentRepository

class AssignmnetRepositoryImp: AssignmentRepository {
    override fun loadAssignment(): Assignment {
        var assignmnet = Assignment()
        assignmnet.title = "assignment1"
        assignmnet.severity = "low"
        return assignmnet
 }
}