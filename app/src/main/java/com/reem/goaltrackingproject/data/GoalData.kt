package com.reem.goaltrackingproject.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "goal_table")
@Parcelize
data class GoalData (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title: String,
    var period : Period ,
    var description : String

 ) : Parcelable