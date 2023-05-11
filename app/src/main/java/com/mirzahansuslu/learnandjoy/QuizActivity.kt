package com.mirzahansuslu.learnandjoy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    //Quiz Layout Buttons
    private var submitButton: Button? = null
    private var feedbackButton: Button? = null

    //Question Text Views
    private var questionTV: TextView? = null
    private var questionNumberTV: TextView? = null

    //Options
    private var optionOneTV: TextView? = null
    private var optionTwoTV: TextView? = null
    private var optionThreeTV: TextView? = null
    private var optionFourTV: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_layout)
        supportActionBar?.hide()

        submitButton = findViewById(R.id.submitButton)
        feedbackButton = findViewById(R.id.feedbackButton)

        questionTV = findViewById(R.id.questionTV)
        questionNumberTV = findViewById(R.id.questionNumberTV)

        optionOneTV = findViewById(R.id.optionOneTV)
        optionTwoTV = findViewById(R.id.optionTwoTV)
        optionThreeTV = findViewById(R.id.optionThreeTV)
        optionFourTV = findViewById(R.id.optionFourTV)

        optionOneTV?.setOnClickListener(this)
        optionTwoTV?.setOnClickListener(this)
        optionThreeTV?.setOnClickListener(this)
        optionFourTV?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.optionOneTV -> {
                optionOneTV?.setBackgroundResource(R.drawable.selected_option_border_bg)
                optionTwoTV?.setBackgroundResource(R.drawable.default_option_border_bg)
                optionThreeTV?.setBackgroundResource(R.drawable.default_option_border_bg)
                optionFourTV?.setBackgroundResource(R.drawable.default_option_border_bg)
            }
            R.id.optionTwoTV -> {
                optionTwoTV?.setBackgroundResource(R.drawable.selected_option_border_bg)
                optionThreeTV?.setBackgroundResource(R.drawable.default_option_border_bg)
                optionFourTV?.setBackgroundResource(R.drawable.default_option_border_bg)
                optionOneTV?.setBackgroundResource(R.drawable.default_option_border_bg)
            }
            R.id.optionThreeTV -> {
                optionThreeTV?.setBackgroundResource(R.drawable.selected_option_border_bg)
                optionTwoTV?.setBackgroundResource(R.drawable.default_option_border_bg)
                optionFourTV?.setBackgroundResource(R.drawable.default_option_border_bg)
                optionOneTV?.setBackgroundResource(R.drawable.default_option_border_bg)
            }
            R.id.optionFourTV -> {
                optionFourTV?.setBackgroundResource(R.drawable.selected_option_border_bg)
                optionTwoTV?.setBackgroundResource(R.drawable.default_option_border_bg)
                optionThreeTV?.setBackgroundResource(R.drawable.default_option_border_bg)
                optionOneTV?.setBackgroundResource(R.drawable.default_option_border_bg)
            }
        }
    }
}