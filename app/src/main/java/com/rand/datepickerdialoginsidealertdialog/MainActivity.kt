package com.rand.datepickerdialoginsidealertdialog

import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.rand.datepickerdialoginsidealertdialog.databinding.ActivityMainBinding
import com.rand.datepickerdialoginsidealertdialog.databinding.DialogInputBinding
import com.rand.datepickerdialoginsidealertdialog.model.Person

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val persons : ArrayList<Person> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        binding.holdContainer.text = String()
        binding.dialogButton.setOnClickListener {
            displayDialog()
        }

        setContentView(view)
    }

    private fun putInHoldContainer() {
        binding.holdContainer.text = String()
        var contentOfHoldContainer = String()
        persons.forEach {
            contentOfHoldContainer += it.person
            contentOfHoldContainer += "\n"
        }
        binding.holdContainer.text = contentOfHoldContainer
    }

    private fun displayDialog() {
        val alertDialog = AlertDialog.Builder(this)
        val inputFormat = DialogInputBinding.inflate(layoutInflater)
        inputFormat.date.text = String()
        inputFormat.dateButton.setOnClickListener {
            displayDate(inputFormat.date)
        }

        alertDialog.setMessage("Enter your information")
        alertDialog.setView(inputFormat.root)
        alertDialog.setPositiveButton("Next",
            DialogInterface.OnClickListener {
                    dialog, which ->
                        val firstValue = inputFormat.nameInput.text.toString().isEmpty()
                        val secondValue = inputFormat.citizenshipInput.text.toString().isEmpty()
                        val thirdValue = inputFormat.date.text.toString().isEmpty()
                        val condition = firstValue || secondValue || thirdValue
                        if (!(condition)) {
                            persons.add(Person(inputFormat.nameInput.text.toString(),
                                inputFormat.citizenshipInput.text.toString(),
                                inputFormat.date.text.toString()))
                        }
                        putInHoldContainer()
                        dialog.dismiss()
            }
        )
        alertDialog.setNegativeButton("Cancel",
            DialogInterface.OnClickListener {
                    dialog, which ->
                        dialog.cancel()
            }
        )
        alertDialog.show()
    }

    private fun displayDate(date: TextView) {
        val dialogDate = DatePickerDialog(this)
        dialogDate.setButton(DatePickerDialog.BUTTON_POSITIVE, "Next",
            DialogInterface.OnClickListener { dialog, which ->
                val day = dialogDate.datePicker.dayOfMonth
                val month = dialogDate.datePicker.month
                val year = dialogDate.datePicker.year
                date.text = formattedDate(day, month, year)
                dialog.dismiss()
            }
        )
        dialogDate.show()
    }

    private fun formattedDate(day: Int, month: Int, year: Int): String {
        return "$day/${month + 1}/$year"
    }
}