package com.mirzahansuslu.learnandjoy

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    private var score = 0
    private var totalQuestions = 0
    private lateinit var scoreTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Get the score and total questions from the intent extras
        score = intent.getIntExtra("SCORE", 0)
        totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)

        // Initialize the score TextView and display the score
        scoreTextView = findViewById(R.id.scoreTextView)
        scoreTextView.text = "You scored $score out of $totalQuestions"
    }
}