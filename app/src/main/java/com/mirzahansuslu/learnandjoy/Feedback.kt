package com.mirzahansuslu.learnandjoy
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast

class Feedback(private val context: Context) {
    @SuppressLint("MissingInflatedId")
    fun show() {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Feedback")

        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.feedback_dialog_layout, null)
        dialogBuilder.setView(dialogView)

        val feedbackEditText = dialogView.findViewById<EditText>(R.id.feedbackEditText)

        dialogBuilder.setPositiveButton("Send") { dialog, which ->
            val feedback = feedbackEditText.text.toString().trim()
            if (feedback.isNotEmpty()) {
                sendFeedback(feedback)
                Toast.makeText(context, "Thank you for your feedback!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please enter your feedback", Toast.LENGTH_SHORT).show()
            }
        }

        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun sendFeedback(feedback: String) {
        // Implement your logic to send the feedback to the server or handle it accordingly
        // You can use this method to perform any necessary operations with the feedback data.
        // For simplicity, this example doesn't include the implementation of sending feedback.
    }
}