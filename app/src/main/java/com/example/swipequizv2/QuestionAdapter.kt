package com.example.swipequizv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter (var onItemClick: ((Question) -> Unit)?, private val questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        var viewHolder = ViewHolder(itemView)
        return viewHolder
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return questions.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])

        holder.itemView.setOnClickListener { view -> Toast.makeText(view.context, questions[position].answer, Toast.LENGTH_LONG).show() }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvQuestion: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(question: Question) {
            tvQuestion.text = question.question
        }

        init {
            tvQuestion.setOnClickListener {
                onItemClick?.invoke(questions[adapterPosition])
            }
        }

    }

}