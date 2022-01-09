package com.reem.goaltrackingproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GoalDao {

    @Query("SELECT * FROM goal_table ORDER BY id ASC")
    fun getAllDate():LiveData<List<GoalData>>

       // when add same date will ignore it
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDate(goalDate : GoalData)
}