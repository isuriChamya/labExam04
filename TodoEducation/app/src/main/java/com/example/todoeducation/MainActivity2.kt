package com.example.todoeducation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoeducation.database.TaskDatabase
import com.example.todoeducation.database.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private lateinit var adapter: TaskAdapter
    private lateinit var viewModel:MainActivityData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val recyclerView:RecyclerView = findViewById(R.id.rvTaskList)
        val repository = TaskRepository(TaskDatabase.getInstance(this))
        viewModel = ViewModelProvider(this)[MainActivityData::class.java]

        viewModel.data.observe(this){
            adapter = TaskAdapter(it,repository,viewModel)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllTaskItems()

            runOnUiThread{
                viewModel.setData(data)
            }
        }

        val addItem: Button = findViewById(R.id.btnAddTask)

        addItem.setOnClickListener {
            displayAlert(repository)
        }

        private fun displayAlert(repository: TaskRepository){
            val builder = AlertDialog.Builder(this)

            builder.setTitle(getText(R.string.alertTitle))
            builder.setMessage("Enter the task item below: ")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("Ok") { dialog, which ->
                val item = input.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    repository.insert(Task(item))
                }
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
        }

        //Create and show the alert dialog
        val alertDialog = builder.create()
        alertDialog.show()


    }
}