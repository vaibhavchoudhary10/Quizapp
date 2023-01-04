package com.example.quizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestions(): ArrayList<QuizQuestion>{

        val questions = ArrayList<QuizQuestion>()

        val q1 = QuizQuestion(
            1,
            "What is the Current Year?",
            "2001",
            "2010",
            "2022",
            "2023",
            3)

        questions.add(q1)

        val q2 = QuizQuestion(
            1,
            "What is the Current Month?",
            "Jan",
            "Dec",
            "Feb",
            "Mar",
            2)

        questions.add(q2)

        val q3 = QuizQuestion(
            1,
            "Who is the PrimeMinister of India?",
            "Amit Shah",
            "RamNath Kovind",
            "Narendra Modi",
            "Rahul Gandhi",
            3)

        questions.add(q3)

        val q4 = QuizQuestion(
            1,
            "What is the full form of xml?",
            "Exclusive Marking Limit",
            "Extensible Markup Language",
            "Extended Markup Language",
            "Extended Marking Limit",
            2)

        questions.add(q4)

        val q5 = QuizQuestion(
            1,
            "Where is Surat situated?",
            "Madhya Pradesh",
            "Gujrat",
            "Delhi",
            "West Bengal",
            2)

        questions.add(q5)

        return questions
    }
}