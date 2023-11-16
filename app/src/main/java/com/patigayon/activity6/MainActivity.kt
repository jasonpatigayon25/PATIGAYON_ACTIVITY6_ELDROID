package com.patigayon.activity6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var alertDialogSelection: TextView
    private lateinit var datePickerSelection: TextView
    private lateinit var timePickerSelection: TextView
    private lateinit var customDialogSelection: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val buttonClick = AnimationUtils.loadAnimation(this, R.anim.button_click)

            alertDialogSelection = findViewById(R.id.alertDialogSelection)

            alertDialogSelection = findViewById(R.id.alertDialogSelection)
            datePickerSelection = findViewById(R.id.datePickerSelection)
            timePickerSelection = findViewById(R.id.timePickerSelection)
            customDialogSelection = findViewById(R.id.customDialogSelection)


            val alertDialogButton = findViewById<Button>(R.id.alertDialogButton)
            alertDialogButton.setOnClickListener {
                it.startAnimation(buttonClick)
                showAlertDialog()
            }

            val datePickerDialogButton = findViewById<Button>(R.id.datePickerDialogButton)
            datePickerDialogButton.setOnClickListener {
                it.startAnimation(buttonClick)
                showDatePickerDialog()
            }

            val timePickerDialogButton = findViewById<Button>(R.id.timePickerDialogButton)
            timePickerDialogButton.setOnClickListener {
                it.startAnimation(buttonClick)
                showTimePickerDialog()
            }

            val customDialogButton = findViewById<Button>(R.id.customDialogButton)
            customDialogButton.setOnClickListener {
                it.startAnimation(buttonClick)
                showCustomDialog()
            }
        }

    private fun showAlertDialog() {
        val editText = EditText(this)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("This is an alert dialog.")
        builder.setView(editText)
        builder.setPositiveButton("OK") { dialog, which ->
            val userInput = editText.text.toString()
            alertDialogSelection.text = "Alert response: $userInput"
            Toast.makeText(applicationContext, "Pressed OK!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            Toast.makeText(applicationContext, "Cancelled!", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { _, selectedYear, monthOfYear, dayOfMonth ->
            val dateMsg = "$dayOfMonth/${monthOfYear + 1}/$selectedYear"
            datePickerSelection.text = "Date chosen: $dateMsg"
            Toast.makeText(applicationContext, dateMsg, Toast.LENGTH_SHORT).show()
        }, year, month, day)

        dpd.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val timeMsg = "$selectedHour:$selectedMinute"
            timePickerSelection.text = "Time chosen: $timeMsg"
            Toast.makeText(applicationContext, timeMsg, Toast.LENGTH_SHORT).show()
        }, hour, minute, true)

        tpd.show()
    }

    private fun showCustomDialog() {
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog_layout, null)
        val customDialogEditText = dialogView.findViewById<EditText>(R.id.customDialogEditText)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Custom Dialog")
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.customDialogButton).setOnClickListener {
            val userInput = customDialogEditText.text.toString()
            customDialogSelection.text = "Custom dialog input: $userInput"
            dialog.dismiss()
            Toast.makeText(applicationContext, "Submitted: $userInput", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }
}
