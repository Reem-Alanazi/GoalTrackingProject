package com.reem.goaltrackingproject

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.time.*

class CustomCountDownTimer (var mutableLiveData: MutableLiveData<String>) {

    lateinit var timer: CountDownTimer
    val zone = ZoneId.systemDefault()
    val startDateTime: ZonedDateTime = LocalDateTime.now().atZone(zone)

    fun start(endOn: Long) {
        if (this::timer.isInitialized) {
            return
        }
        timer = object : CountDownTimer(endOn * 1000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                val stringBuilder = StringBuilder()

                val endDateTime: ZonedDateTime =
                    Instant.ofEpochMilli(millisUntilFinished).atZone(ZoneId.systemDefault())
                        .toLocalDateTime().atZone(zone)

                var diff: Duration = Duration.between(startDateTime, endDateTime)
                if (diff.isZero() || diff.isNegative) {
                    stringBuilder.append("Already ended!")
                } else {
                    val days: Long = diff.toDays()

                    if (days != 0L) {
                        stringBuilder.append("${days}day : ")
                        diff = diff.minusDays(days)
                    }

                    val hours: Long = diff.toHours()
                    stringBuilder.append("${hours}hr : ")
                    diff = diff.minusHours(hours)

                    val minutes: Long = diff.toMinutes()
                    stringBuilder.append("${minutes}min : ")
                    diff = diff.minusMinutes(minutes)

                    val seconds: Long = diff.getSeconds()

                    stringBuilder.append("${seconds}sec")

                }

                mutableLiveData.postValue(stringBuilder.toString())
                Log.d("CustomCountDownTimer", stringBuilder.toString())
            }

            override fun onFinish() {
            }
        }

        timer.start()
    }

}