package com.example.testchat.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.testchat.data.network.RetrofitClient
import com.example.testchat.databinding.FragmentSettingsBinding
import com.example.testchat.ui.auth.AuthActivity
import com.example.testchat.data.network.ApiService
import com.example.testchat.data.storage.TokenManager

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var apiService: ApiService // Добавляем объявление сервиса

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        apiService = RetrofitClient.getApiService(requireContext()) // Инициализация сервиса
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val login = TokenManager.getLogin(requireContext())
        binding.tvUserEmail.text = login ?: "Неизвестный пользователь"

        binding.btnLogout.setOnClickListener {
            TokenManager.deleteToken(requireContext())

            val intent = Intent(requireContext(), AuthActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            requireActivity().finish()
        }

        val prefs = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isDarkTheme = prefs.getBoolean("dark_theme", false)
        binding.switchTheme.isChecked = isDarkTheme

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            val mode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(mode)
            prefs.edit().putBoolean("dark_theme", isChecked).apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}