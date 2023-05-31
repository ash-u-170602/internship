package com.example.internship

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.internship.databinding.DialogueLayoutBinding

class CustomDialogue : AppCompatDialogFragment() {
    private val binding by lazy { DialogueLayoutBinding.inflate(layoutInflater) }
    private lateinit var listener: DialogListener


    interface DialogListener {
        fun applyTexts(name: String, price: Int)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement DialogListener")
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        builder.setView(binding.root)
            .setTitle("Add Name and Price")

        binding.add.setOnClickListener {
            val name = binding.name.text.toString()
            val price = binding.price.text.toString()
            if (name == "" || price == "") {
                Toast.makeText(requireContext(), "Enter valid inputs", Toast.LENGTH_SHORT).show()
            } else {
                listener.applyTexts(name, price.toInt())
                dismiss()
            }
        }
        return builder.create()
    }
}