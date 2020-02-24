package com.example.swipequizv2

data class Question(
    var question: String,
    var answer: String
){ companion object {
    val QUESTIONS = arrayOf(
        "This question is true",
        "This question is false",
        "This is also true"
    )
    val ANSWERS = arrayOf(
        "true",
        "false",
        "true"
    )
}

}