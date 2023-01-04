package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultView : AppCompatActivity() {
    private var mUserName: String? = null
    private var mCorrectAnswers: String? = null
    private var mTotalQuestions: String? = null
    private var tvName: TextView? = null
    private var tvScoreMessage: TextView? = null
    private var btnFinish: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_view)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mCorrectAnswers = intent.getStringExtra(Constants.CORRECT_ANSWERS)
        mTotalQuestions = intent.getStringExtra(Constants.TOTAL_QUESTIONS)

        var scoreMessage: String = "You Scored $mCorrectAnswers out of $mTotalQuestions"

        tvName = findViewById(R.id.tv_name)
        tvName?.text = mUserName

        tvScoreMessage = findViewById(R.id.tv_score)
        tvScoreMessage?.text = scoreMessage

        btnFinish = findViewById(R.id.btn_finish)
        btnFinish?.setOnClickListener(){
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }


    }
}