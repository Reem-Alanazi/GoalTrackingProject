package com.reem.goaltrackingproject.data

import androidx.lifecycle.LiveData

class GoalRepository (private val goalDao: GoalDao){

    val getAllData:LiveData<List<GoalData>> = goalDao.getAllDate()

    suspend fun insertData(goalData : GoalData){
        goalDao.insertDate(goalData)
    }

    suspend fun updateData(goalData: GoalData){
        goalDao.updateData(goalData)
    }
}