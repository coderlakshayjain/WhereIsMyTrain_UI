package com.example.whereismytraindemo

import android.widget.EditText
import android.widget.ImageView

class ClearActionUtil {
    companion object {
        fun setClearAction(clearImageView: ImageView, editText: EditText) {
            clearImageView.setOnClickListener {
                editText.text.clear()
            }
        }
    }
}