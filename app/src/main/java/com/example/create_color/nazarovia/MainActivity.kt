package com.example.create_color.nazarovia

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        val dialog = Dialog(this)
        dialogBinding = DialogColorPickerBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.setOnDismissListener{
            updateColorPreview()
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

    private fun updateColorPreview() {
        binding.colorPreview.setBackgroundColor(Color.rgb(dialogBinding.red.text.toString().toIntOrNull() ?: 0, dialogBinding.green.text.toString().toIntOrNull() ?: 0, dialogBinding.blue.text.toString().toIntOrNull() ?: 0))
    }
}