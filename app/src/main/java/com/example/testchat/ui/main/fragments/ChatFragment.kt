package com.example.testchat.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testchat.databinding.FragmentChatBinding
import androidx.navigation.fragment.findNavController

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Обработка нажатия на кнопку "Назад"
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack() // Возврат на предыдущий фрагмент
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}