package com.example.mvvmtimer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmtimer.databinding.ActivityMainBinding // Якщо цей рядок червоний, видаліть його, наведіть курсор на ActivityMainBinding нижче і натисніть Alt+Enter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.timerLiveData.observe(this) { seconds ->
            binding.tvTimer.text = "залишилось: $seconds с"
        }

        viewModel.isTimerRunning.observe(this) { isRunning ->
            binding.btnStart.isEnabled = !isRunning
        }

        binding.btnStart.setOnClickListener {
            val userInput = binding.etTimeInput.text.toString().toLongOrNull()
            viewModel.startTimer(userInput)
        }

        binding.btnReset.setOnClickListener {
            viewModel.resetTimer()
            binding.etTimeInput.text.clear()
        }
    }
}