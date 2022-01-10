package com.reem.goaltrackingproject.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
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


}