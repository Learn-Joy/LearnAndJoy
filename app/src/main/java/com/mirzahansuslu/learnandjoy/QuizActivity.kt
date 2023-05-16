package com.mirzahansuslu.learnandjoy

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    //Firestore instance
    private val db = FirebaseFirestore.getInstance()

    //Quiz Layout Buttons
    private lateinit var submitButton: Button
    private lateinit var feedbackButton: Button
    private lateinit var previousButton: Button

    //Question Text Views
    private lateinit var questionTV: TextView
    private lateinit var questionNumberTV: TextView

    //Options
    private lateinit var optionOneTV: TextView
    private lateinit var optionTwoTV: TextView
    private lateinit var optionThreeTV: TextView
    private lateinit var optionFourTV: TextView

    //List of Questions
    private lateinit var questionList: MutableList<Question>

    //Current Question Index
    private var currentQuestionIndex = 0

    //Selected Answer
    private var selectedAnswer = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_layout)
        supportActionBar?.hide()

        // initialize buttons
        submitButton = findViewById(R.id.submitButton)
        feedbackButton = findViewById(R.id.feedbackButton)
        previousButton = findViewById(R.id.previousButton)

        // initialize text views
        questionTV = findViewById(R.id.questionTV)
        questionNumberTV = findViewById(R.id.questionNumberTV)

        // initialize options
        optionOneTV = findViewById(R.id.optionOneTV)
        optionTwoTV = findViewById(R.id.optionTwoTV)
        optionThreeTV = findViewById(R.id.optionThreeTV)
        optionFourTV = findViewById(R.id.optionFourTV)

        // set click listeners
        optionOneTV.setOnClickListener(this)
        optionTwoTV.setOnClickListener(this)
        optionThreeTV.setOnClickListener(this)
        optionFourTV.setOnClickListener(this)
        submitButton.setOnClickListener(this)
       /* feedbackButton.setOnClickListener {
            displayFeedbackDialog()
        }*/
        previousButton.setOnClickListener(this)

        // fetch questions from Firestore


        // fetch questions from Firestore
        fetchQuestions()
    }

    private fun fetchQuestions() {
        db.collection("questions")
            .get()
            .addOnSuccessListener { result ->
                questionList = result.toObjects(Question::class.java)
                setQuestionUI()
            }
            .addOnFailureListener { exception ->
                // handle exception
            }
    }

    private fun setQuestionUI() {
        // get current question
        val currentQuestion = questionList[currentQuestionIndex]

        // update question text views
        questionTV.text = currentQuestion.question
        questionNumberTV.text =
            getString(R.string.question_number_text,currentQuestionIndex + 1, questionList.size)

        // update options text views
        optionOneTV.text = currentQuestion.optionOne
        optionTwoTV.text = currentQuestion.optionTwo
        optionThreeTV.text = currentQuestion.optionThree
        optionFourTV.text = currentQuestion.optionFour

        // reset selected answer
        selectedAnswer = -1

        // reset background of options
        makeAllDefaultBackground()

        // enable/disable previous button based on current question index
        val previousButton: Button = findViewById(R.id.previousButton)
        previousButton.isEnabled = currentQuestionIndex > 0
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.optionOneTV -> {
                makeAllDefaultBackground()
                optionOneTV.setBackgroundResource(R.drawable.selected_option_border_bg)
                selectedAnswer = 1
            }
            R.id.optionTwoTV -> {
                makeAllDefaultBackground()
                optionTwoTV.setBackgroundResource(R.drawable.selected_option_border_bg)
                selectedAnswer = 2
            }
            R.id.optionThreeTV -> {
                makeAllDefaultBackground()
                optionThreeTV.setBackgroundResource(R.drawable.selected_option_border_bg)
                selectedAnswer = 3
            }
            R.id.optionFourTV -> {
                makeAllDefaultBackground()
                optionFourTV.setBackgroundResource(R.drawable.selected_option_border_bg)
                selectedAnswer = 4
            }
            R.id.submitButton -> {
                makeAllDefaultBackground()
                if (selectedAnswer == -1) {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                } else {
                    checkAnswer()
                    // Set the selected answer for the current question
                    questionList[currentQuestionIndex].selectedAnswer = selectedAnswer.toString()
                    moveToNextQuestion()
                }
            }
            R.id.previousButton -> {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--
                    setQuestionUI()
                } else {
                    // do nothing, already at first question
                }
            }
        }
    }

 /*   private fun moveToNextQuestion() {
        if (currentQuestionIndex < questionList.size - 1) {
            currentQuestionIndex++
            setQuestionView()
        } *//*else {
            // End of quiz, show score
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL_QUESTIONS", questionList.size)
            startActivity(intent)
            finish()
        }*//*

        selectedAnswer = -1 // reset selected answer for the next question
    }*/


    private fun moveToNextQuestion() {
        if (currentQuestionIndex < questionList.size - 1) {
            currentQuestionIndex++
            setQuestionUI()
        } else {
            // End of quiz, show score
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL_QUESTIONS", questionList.size)
            startActivity(intent)
            finish()
        }

        selectedAnswer = -1 // reset selected answer for the next question
    }

    private fun makeAllDefaultBackground() {
        optionOneTV?.setBackgroundResource(R.drawable.default_option_border_bg)
        optionTwoTV?.setBackgroundResource(R.drawable.default_option_border_bg)
        optionThreeTV?.setBackgroundResource(R.drawable.default_option_border_bg)
        optionFourTV?.setBackgroundResource(R.drawable.default_option_border_bg)
    }

    private var score = 0 // add a score variable to the class
    private fun checkAnswer() {
        val currentQuestion = questionList[currentQuestionIndex]
        if (selectedAnswer.toString().equals(currentQuestion.correctAnswer)) {
            // answer is correct
            score++
            // Add your code here to handle a correct answer
        } else {
            Log.d("Check Answer", "Correct answer: ${currentQuestion.correctAnswer}")
            // answer is incorrect
            // Add your code here to handle an incorrect answer
        }
    }

    private fun setQuestionView() {
        val question = questionList[currentQuestionIndex]
        questionTV?.text = question.question
        questionNumberTV?.text = "Question ${currentQuestionIndex + 1}"
        optionOneTV?.text = question.optionOne
        optionTwoTV?.text = question.optionTwo
        optionThreeTV?.text = question.optionThree
        optionFourTV?.text = question.optionFour
    }

}

