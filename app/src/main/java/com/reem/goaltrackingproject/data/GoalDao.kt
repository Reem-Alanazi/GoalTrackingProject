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


}