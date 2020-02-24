package com.example.swipequizv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    //DOES NOT WORK PERFECTLY!!!!

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter({question -> Toast.makeText(this, question.answer, Toast.LENGTH_LONG) }, questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        // Initialize the recycler view with a linear layout manager, adapter
        rvQuestions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter

        rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
        questionAdapter.notifyDataSetChanged()
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */

    private fun createItemTouchHelper(): ItemTouchHelper {


        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                checkAnswer(questions.get(position), direction)
                questions.removeAt(position)
                questionAdapter.notifyDataSetChanged()
            }

        }
        return ItemTouchHelper(callback)
    }

    private fun checkAnswer(question: Question, yourAnswer: Int){

        var yourAnswerString = ""

        if (yourAnswer == ItemTouchHelper.LEFT) { yourAnswerString = getString(R.string.correct) }
        if (yourAnswer == ItemTouchHelper.RIGHT) { yourAnswerString = getString(R.string.incorrect)}

        if (question.answer == yourAnswerString) {
            onAnswerCorrect(question.answer)
        }else{
            onAnswerIncorrect(question.answer)
        }
    }

    private fun onAnswerCorrect(answer: String){
        var snackbarMessage = getString(R.string.correct_snackbar) + (answer)
        val snackbar =
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), snackbarMessage, Snackbar.LENGTH_LONG)
        snackbar.show()

    }

    private fun onAnswerIncorrect(answer: String){
        var snackbarMessage = getString(R.string.incorrect_snackbar) + (answer)
        val snackbar =
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), snackbarMessage, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}
