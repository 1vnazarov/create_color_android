package com.example.create_color.nazarovia

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.create_color.nazarovia.databinding.ActivityMainBinding
import com.example.create_color.nazarovia.databinding.DialogColorPickerBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: DialogColorPickerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        dialogBinding = DialogColorPickerBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this).setTitle("Создай цвет!").setView(dialogBinding.root).setOnDismissListener{
            updateColorPreview()
        }.setPositiveButton("OK", null).create()
            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    if (!(validate(dialogBinding.red) && validate(dialogBinding.green) && validate(dialogBinding.blue)))
                        return@setOnClickListener
                    updateColorPreview()
                    dialog.dismiss()
                }
            }

        /*dialogBinding.red.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateColorPreview()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        dialogBinding.green.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateColorPreview()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        dialogBinding.blue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateColorPreview()
            }

            override fun afterTextChanged(s: Editable?) {}
        })*/

        dialog.show()
    }

    private fun validate(textbox: EditText): Boolean {
        val value = textbox.text.toString().toIntOrNull()
        return if (!(value != null && value in 0..255)) {
            textbox.setBackgroundColor(Color.RED)
            false
        } else {
            textbox.setBackgroundColor(Color.TRANSPARENT)
            true
        }
    }
    private fun updateColorPreview() {
        binding.colorPreview.setBackgroundColor(
            Color.rgb(
                dialogBinding.red.text.toString().toIntOrNull() ?: 0,
                dialogBinding.green.text.toString().toIntOrNull() ?: 0,
                dialogBinding.blue.text.toString().toIntOrNull() ?: 0
            )
        )
    }
}