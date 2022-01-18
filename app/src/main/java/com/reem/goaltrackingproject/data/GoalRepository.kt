package com.reem.goaltrackingproject.data

import androidx.lifecycle.LiveData

class GoalRepository (private val goalDao: GoalDao){

    val getAllData: LiveData<List<GoalData>> = goalDao.getAllDate()
    val sortByDay:  LiveData<List<GoalData>> = goalDao.sortByDayPeriod()
    val sortByWeek: LiveData<List<GoalData>> = goalDao.sortByWeekPeriod()
    val sortByMonth: LiveData<List<GoalData>> = goalDao.sortByMonthPeriod()


    suspend fun insertData(goalData : GoalData){
        goalDao.insertDate(goalData)
    }

    suspend fun updateData(goalData: GoalData){
        goalDao.updateData(goalData)
    }

    suspend fun deleteItem(goalData: GoalData){
        goalDao.deleteItem(goalData)
    }

    suspend fun deleteAll(){
        goalDao.deleteAll()
    }

    fun searchDatabase(goalSearchQuery: String): LiveData<List<GoalData>>{
      return goalDao.searchInDatabase(goalSearchQuery)
    }
}