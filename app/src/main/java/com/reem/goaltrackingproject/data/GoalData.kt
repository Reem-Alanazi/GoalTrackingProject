package com.reem.goaltrackingproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal_table")
data class GoalData (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title: String,
    var period : Period ,
    var description : String

 )