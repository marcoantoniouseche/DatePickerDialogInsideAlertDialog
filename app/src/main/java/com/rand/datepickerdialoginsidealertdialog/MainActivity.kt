package com.rand.datepickerdialoginsidealertdialog

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        val dialog = AlertDialog.Builder(this)
        val inputFormat = DialogInputBinding.inflate(layoutInflater)
        inputFormat.nameInput.width = 350
        inputFormat.citizenshipInput.width = 350

        dialog.setMessage("Enter your information")
        dialog.setView(inputFormat.root)
        dialog.setPositiveButton("Next",
            DialogInterface.OnClickListener {
                    dialog, which ->
                        if (!(inputFormat.nameInput.text.isEmpty()
                            || inputFormat.citizenshipInput.text.isEmpty())) {
                                persons.add(Person(inputFormat.nameInput.text.toString(),
                                    inputFormat.citizenshipInput.text.toString(),
                                    "24/11/2011"))
                        }
                        putInHoldContainer()
                        dialog.dismiss()
            }
        )
        dialog.setNegativeButton("Cancel",
            DialogInterface.OnClickListener {
                    dialog, which ->
                        dialog.cancel()
            }
        )
        dialog.show()
    }
}