package com.reem.goaltrackingproject.viewmodel

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.data.Period

class SharedViewModel(application: Application):AndroidViewModel(application){

    // if null or not
     fun verifyDataFromUser(title: String, description : String ): Boolean{
        return if(TextUtils.isEmpty(title)|| TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty() || description.isEmpty())

    }

     fun convertPeriod(period : String): Period {
        return when(period){
            "Day" -> (Period.DAY)
            "Week" -> (Period.WEEK)
            "Month" -> (Period.MONTH)
            else -> (Period.YEAR)
        }
    }

    // color period text when user pike from spinner
    val listener: AdapterView.OnItemSelectedListener = object :

        AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
              0 -> {(parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.black))}
              1 -> {(parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.black))}
              2 -> {(parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.black))}
              3 -> {(parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.black))}
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }


     fun parasPeriod(period: Period):Int{
        return when(period){
            Period.DAY -> 0
            Period.WEEK -> 1
            Period.MONTH -> 2
            Period.YEAR -> 3
        }
    }




}