package com.example.lab3android

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StorageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        val textView = findViewById<TextView>(R.id.text_storage)
        val editText = findViewById<EditText>(R.id.edit_update)
        val buttonUpdate = findViewById<Button>(R.id.button_update)
        val buttonDelete = findViewById<Button>(R.id.button_delete)

        val sharedPref = getSharedPreferences("FontData", Context.MODE_PRIVATE)
        val text = sharedPref.getString("text", null)
        val fontSize = sharedPref.getInt("fontSize", -1)

        if (text == null || fontSize == -1) {
            textView.text = "Дані відсутні"
        } else {
            textView.text = text
            textView.textSize = fontSize.toFloat()
        }

        buttonUpdate.setOnClickListener {
            val newText = editText.text.toString().trim()
            if (newText.isEmpty()) {
                Toast.makeText(this, "Новий текст не може бути порожнім", Toast.LENGTH_SHORT).show()
            } else {
                with(sharedPref.edit()) {
                    putString("text", newText)
                    apply()
                }
                textView.text = newText
                Toast.makeText(this, "Текст оновлено", Toast.LENGTH_SHORT).show()
            }
        }

        buttonDelete.setOnClickListener {
            with(sharedPref.edit()) {
                clear()
                apply()
            }
            textView.text = "Дані видалено"
            Toast.makeText(this, "Дані успішно видалено", Toast.LENGTH_SHORT).show()
        }
    }
}