package com.example.todoeducation

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TaskViewHolder(view : View) : ViewHolder(view) {
    val cbTask : CheckBox = view.findViewById(R.id.cbTask)
    val ivDelete : ImageView = view.findViewById(R.id.ivDelete)
}