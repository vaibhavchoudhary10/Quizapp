package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var tvQuestion: TextView? = null
    private var tvOption1: TextView? = null
    private var tvOption2: TextView? = null
    private var tvOption3: TextView? = null
    private var tvOption4: TextView? = null
    private var tvProgress: TextView? = null
    private var btnSubmit: Button? = null
    private var progressBar: ProgressBar? = null
    private var mQuestionNumber = 1
    private var mQuestions: ArrayList<QuizQuestion>? = null
    private var mSelectedAnswer: Int = 0
    private var mCorrectAnswer: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestions = Constants.getQuestions()


        tvQuestion = findViewById(R.id.tv_question)
        btnSubmit = findViewById(R.id.btn_submit)
        tvOption1= findViewById(R.id.tv_op1)
        tvOption2= findViewById(R.id.tv_op2)
        tvOption3= findViewById(R.id.tv_op3)
        tvOption4= findViewById(R.id.tv_op4)
        tvProgress= findViewById(R.id.tv_progress)
        progressBar= findViewById(R.id.progress_bar)

        tvOption1?.setOnClickListener(this)
        tvOption2?.setOnClickListener(this)
        tvOption3?.setOnClickListener(this)
        tvOption4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        setQuestion()

    }

    override fun onClick(view: View){
        when(view.id){
            R.id.tv_op1 ->{
                tvOption1?.let {
                    selectedOption(it, 1)
                }
            }
            R.id.tv_op2 ->{
                tvOption2?.let {
                    selectedOption(it, 2)
                }
            }
            R.id.tv_op3 ->{
                tvOption3?.let {
                    selectedOption(it, 3)
                }
            }
            R.id.tv_op4 ->{
                tvOption4?.let {
                    selectedOption(it, 4)
                }
            }
            R.id.btn_submit -> {
                if(mSelectedAnswer == 0){
                    mQuestionNumber++
                    when{
                        mQuestionNumber <= mQuestions!!.size -> {
                            setQuestion()
                        } else -> {
                            btnSubmit?.text = "show result"
                            val intent = Intent(this, ResultView::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestions!!.size.toString())
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer.toString())
                            startActivity(intent)
                        }
                    }
                } else{
                    val ques = mQuestions!![mQuestionNumber - 1]
                    if(ques.correctOption != mSelectedAnswer){
                        paintSelectedAnswer(mSelectedAnswer , R.drawable.wrong_option_bg)
                    } else{
                        mCorrectAnswer++
                        paintSelectedAnswer(mSelectedAnswer , R.drawable.correct_option_bg)
                    }
                    if(mQuestionNumber == mQuestions!!.size){
                        btnSubmit?.text = "Show Result"
                    } else{
                        btnSubmit?.text = "Next"
                    }
                    mSelectedAnswer = 0
                }
            }
        }
    }

    private fun paintSelectedAnswer(answer: Int, drawableView: Int) {
        when(answer){
            1 -> {
                tvOption1?.background = ContextCompat.getDrawable(
                this@QuestionsActivity, drawableView)
            }
            2 -> {
                tvOption2?.background = ContextCompat.getDrawable(
                this@QuestionsActivity, drawableView)
            }
            3 -> {
                tvOption3?.background = ContextCompat.getDrawable(
                this@QuestionsActivity, drawableView)
            }
            4 -> {
                tvOption4?.background = ContextCompat.getDrawable(
                this@QuestionsActivity, drawableView)
            }
        }
    }

    private fun selectedOption(textViewOption: TextView,selectedOption: Int){

        setDefaultBackground()

        mSelectedAnswer = selectedOption
        textViewOption.setTextColor(Color.parseColor("#00005C"))
        textViewOption.setTypeface(textViewOption.typeface, Typeface.BOLD)
        textViewOption.background = ContextCompat.getDrawable(
            this@QuestionsActivity, R.drawable.selected_option_bg
        )
    }

    private fun setDefaultBackground() {
        val optionsTextView = ArrayList<TextView>()
        tvOption1?.let{
            optionsTextView.add(0,it)
        }
        tvOption2?.let{
            optionsTextView.add(1,it)
        }
        tvOption3?.let{
            optionsTextView.add(2,it)
        }
        tvOption4?.let{
            optionsTextView.add(3,it)
        }

        for(optionView in optionsTextView){
            optionView.typeface = Typeface.DEFAULT
            optionView.setTextColor(Color.parseColor("#7743DB"))
            optionView.background = ContextCompat.getDrawable(
                this@QuestionsActivity, R.drawable.default_border_option_bg
            )
        }
    }

    private fun setQuestion() {
        val ques: QuizQuestion = mQuestions!![mQuestionNumber-1]
        setDefaultBackground()

        btnSubmit?.text = "Submit"
        tvQuestion?.text = ques.question
        tvOption1?.text = ques.option1
        tvOption2?.text = ques.option2
        tvOption3?.text = ques.option3
        tvOption4?.text = ques.option4
        tvProgress?.text = "$mQuestionNumber" + "/" + progressBar?.max
        progressBar?.progress = mQuestionNumber
    }
}