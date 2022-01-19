package com.reem.goaltrackingproject.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GoalDao {

    @Query("SELECT * FROM goal_table ORDER BY id ASC")
    fun getAllDate():LiveData<List<GoalData>>

       // when add same date will ignore it
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDate(goalDate : GoalData)


    @Update
    suspend fun updateData(goalDate: GoalData)

    @Delete
    suspend fun deleteItem(goalDate: GoalData)

    //Custom query for delete all data on my database
    @Query("DELETE FROM goal_table")
    suspend fun deleteAll()

    //The LIKE command is used in a WHERE clause to search for a specified pattern in a column.
    @Query("SELECT * FROM goal_table WHERE title LIKE :searchQuery")
    fun searchInDatabase(searchQuery: String):LiveData<List<GoalData>>

    @Query("SELECT * FROM goal_table ORDER BY CASE WHEN Period LIKE 'D%' THEN 1 WHEN period Like 'W%' THEN 2 WHEN period LIKE 'M%' THEN 3 WHEN period LIKE 'Y%' THEN 4 END")
    fun sortByDayPeriod(): LiveData<List<GoalData>>

    @Query("SELECT * FROM goal_table ORDER BY CASE WHEN Period LIKE 'W%' THEN 1 WHEN period Like 'D%' THEN 2 WHEN period LIKE 'M%' THEN 3 WHEN period LIKE 'Y%' THEN 4 END")
    fun sortByWeekPeriod(): LiveData<List<GoalData>>

    @Query("SELECT * FROM goal_table ORDER BY CASE WHEN Period LIKE 'M%' THEN 1 WHEN period Like 'D%' THEN 2 WHEN period LIKE 'W%' THEN 3 WHEN period LIKE 'Y%' THEN 4 END")
    fun sortByMonthPeriod(): LiveData<List<GoalData>>

    @Query("SELECT * FROM goal_table ORDER BY CASE WHEN Period LIKE 'Y%' THEN 1 WHEN period Like 'D%' THEN 2 WHEN period LIKE 'W%' THEN 3 WHEN period LIKE 'M%' THEN 4 END")
    fun sortByYearPeriod(): LiveData<List<GoalData>>


}