package com.example.lab3android

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class OutputFragment : Fragment(R.layout.fragment_output) {
    companion object {
        fun newInstance(text: String, fontSize: Int) = OutputFragment().apply {
            arguments = Bundle().apply {
                putString("TEXT", text)
                putInt("FONT_SIZE", fontSize)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.text_result)
        val buttonCancel = view.findViewById<Button>(R.id.button_cancel)

        textView.text = arguments?.getString("TEXT")
        textView.textSize = arguments?.getInt("FONT_SIZE")?.toFloat() ?: 16f

        buttonCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}