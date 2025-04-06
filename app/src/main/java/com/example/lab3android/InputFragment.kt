package com.example.lab3android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab3android.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOk.setOnClickListener {
            val text = binding.editText.text.toString().trim()

            if (text.isBlank()) {
                Toast.makeText(requireContext(), "Введіть текст!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val checkedId = binding.radioGroup.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(requireContext(), "Оберіть розмір шрифту!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadio = binding.root.findViewById<RadioButton>(checkedId)
            val fontSize = selectedRadio.text.toString().replace("sp", "").toInt()

            val prefs = requireActivity().getSharedPreferences("FontPrefs", 0)
            prefs.edit()
                .putString("text", text)
                .putInt("fontSize", fontSize)
                .apply()

            Toast.makeText(requireContext(), "Збережено!", Toast.LENGTH_SHORT).show()

            val fragment = OutputFragment.newInstance(text, fontSize)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        binding.buttonOpen.setOnClickListener {
            val intent = Intent(requireContext(), StorageActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
