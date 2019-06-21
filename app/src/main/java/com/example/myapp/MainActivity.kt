package com.example.myapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapp.Impl.AssignmnetRepositoryImp
import com.example.myapp.Impl.HomeImpl
import com.example.myapp.Interface.HomePresenter
import com.example.myapp.Interface.Homeview
import android.R.layout.simple_list_item_1
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.*
import com.example.myapp.Impl.Assignment
import com.example.myapp.R.id.fab
import com.example.myapp.R.id.home
import com.example.myapp.Responses.InsertResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_assignment.*
import kotlinx.android.synthetic.main.add_assignment.view.*

class MainActivity : AppCompatActivity(), Homeview {
    private lateinit var homePresenter: HomePresenter
    var dataSource = ArrayList<Assignment>()
    private lateinit var fab: FloatingActionButton
    var severity:String = "low"
    private lateinit var adapter: LVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab = findViewById(R.id.fab)
        homePresenter = HomeImpl(this, AssignmnetRepositoryImp())
        homePresenter.findAssignment(this)
        adapter = LVAdapter(this, dataSource)
        lvLanguage.adapter = adapter
        fab.setOnClickListener { _ -> showModal() }
    }

    override fun response(resp: ArrayList<Assignment>) {
        dataSource.clear()
        dataSource.addAll(resp)
    }

    private fun showModal() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_assignment, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Add Assignmnet")
        val  mAlertDialog = mBuilder.show()
        mAlertDialog.severity.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioLow -> severity = "low"
                R.id.radioMedium -> severity = "medium"
                else -> severity = "high"
            }
        }

        mDialogView.dialogAddAssigmentBtn.setOnClickListener {
            mAlertDialog.dismiss()
            val title = mDialogView.titleTextField.text.toString()
            val desc = mDialogView.descriptionTextField.text.toString()
            val assignment = Assignment()
            assignment.title = title
            assignment.description = desc
            assignment.severity = severity
            homePresenter.insertAssignmentToDB(this, assignment)
        }
        mDialogView.dialogCancelBtn.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    override fun showToast(response: InsertResponse) {
        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
    }
    override fun reloadData() {
        homePresenter.findAssignment(this)
    }
}
