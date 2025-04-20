package com.kunvarpreet.todolistapp
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskCheckbox: CheckBox = itemView.findViewById(R.id.task_checkbox)
        val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskCheckbox.setOnCheckedChangeListener(null)
        holder.taskTitle.text = task.title
        holder.taskCheckbox.isChecked = task.isDone

        holder.taskTitle.paintFlags = if (task.isDone)
            holder.taskTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else
            holder.taskTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        holder.taskCheckbox.setOnCheckedChangeListener { _, isChecked ->
            task.isDone = isChecked

            holder.taskTitle.paintFlags = if (isChecked)
                holder.taskTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else
                holder.taskTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.deleteButton.setOnClickListener {
            onDelete(holder.adapterPosition)
        }
    }

}