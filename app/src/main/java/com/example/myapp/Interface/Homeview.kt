package com.example.myapp.Interface

import com.example.myapp.Impl.Assignment
import com.example.myapp.Responses.InsertResponse

public interface Homeview {

    fun response(resp: ArrayList<Assignment>)
    fun showToast(response: InsertResponse)
    fun reloadData()
}