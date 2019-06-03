package com.example.myapp.Interface

import com.example.myapp.Impl.Assignment

interface AssignmentRepository {

    fun loadAssignment(): Assignment
}