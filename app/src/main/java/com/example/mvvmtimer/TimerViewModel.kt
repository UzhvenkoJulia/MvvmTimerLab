package com.example.mvvmtimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private val timerModel = TimerModel()

    private val _timerLiveData = MutableLiveData<Long>(30)
    val timerLiveData: LiveData<Long> = _timerLiveData

    private val _isTimerRunning = MutableLiveData<Boolean>(false)
    val isTimerRunning: LiveData<Boolean> = _isTimerRunning

    private var currentStartSeconds: Long = 30

    fun startTimer(userSeconds: Long?) {
        val secondsToStart = userSeconds ?: 30L
        currentStartSeconds = secondsToStart

        _isTimerRunning.value = true
        _timerLiveData.value = secondsToStart

        timerModel.start(
            seconds = secondsToStart,
            onTick = { secondsLeft ->
                _timerLiveData.value = secondsLeft
            },
            onFinish = {
                _timerLiveData.value = 0
                _isTimerRunning.value = false
            }
        )
    }

    fun resetTimer() {
        timerModel.stop()
        _isTimerRunning.value = false
        _timerLiveData.value = currentStartSeconds
    }

    override fun onCleared() {
        super.onCleared()
        timerModel.stop()
    }
}