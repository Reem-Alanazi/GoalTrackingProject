package com.reem.goaltrackingproject.viewmodel

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.data.GoalData
import com.reem.goaltrackingproject.data.Period

class SharedViewModel(application: Application):AndroidViewModel(application){

    val emptyDatabase : MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDatabaseEmpty(goalData: List<GoalData>){
        emptyDatabase.value = goalData.isEmpty()
    }

       // if null or not
     fun verifyDataFromUser(title: String, description : String ): Boolean{
        return if(TextUtils.isEmpty(title)|| TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty() || description.isEmpty())

    }

     fun convertPeriod(period : String): Period {
        return when(period){
            "Day" -> {
                Period.DAY
            }
            "Week" -> {
                Period.WEEK
            }
            "Month" -> {
                Period.MONTH
            }
            else -> {
                Period.YEAR
            }
        }
    }

    // color period text when user pike from spinner
    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
              0 -> {(parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
              1 -> {(parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.blue))}
              2 -> {(parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
              3 -> {(parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

    }

     fun convertPeriodToInt(period: Period):Int{
        return when(period){
            Period.DAY -> 0
            Period.WEEK -> 1
            Period.MONTH -> 2
            Period.YEAR -> 3
        }
    }
}