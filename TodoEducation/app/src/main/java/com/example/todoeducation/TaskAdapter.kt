package com.example.todoeducation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todoeducation.database.Task
import com.example.todoeducation.database.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskAdapter(items:List<Task>,repository: TaskRepository,viewModel: MainActivityData):Adapter<TaskViewHolder>() {

    var context: Context? = null
    val items = items
    val repository = repository
    val viewModel = viewModel


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item,parent,false)
        context = parent.context

        return TaskViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.cbTask.text = items.get(position).item
        holder.ivDelete.setOnClickListener {
            val isChecked = holder.cbTask.isChecked

          if (isChecked){
                CoroutineScope(Dispatchers.IO).launch{
                    repository.delete(items.get(position))
                    val data =repository.getAllTaskItems()
                    withContext(Dispatchers.Main){
                        viewModel.setData(data)
                    }
                }
          }else{
              Toast.makeText(context,"Select the item to be deleted",Toast.LENGTH_LONG).show()
          }

        }
    }

}