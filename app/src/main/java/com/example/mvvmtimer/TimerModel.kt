package com.example.mvvmtimer

import android.os.CountDownTimer

class TimerModel {
    private var countDownTimer: CountDownTimer? = null

    // колбеки
    fun start(seconds: Long, onTick: (Long) -> Unit, onFinish: () -> Unit) {
        stop()

        countDownTimer = object : CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                onFinish()
            }
        }.start()
    }

    fun stop() {
        countDownTimer?.cancel()
        countDownTimer = null
    }
}