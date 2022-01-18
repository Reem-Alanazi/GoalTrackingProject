package com.reem.goaltrackingproject

import androidx.recyclerview.widget.DiffUtil
import com.reem.goaltrackingproject.data.GoalData

class GoalDiffUtil
    (private val oldGoalList: List<GoalData>,
     private val newGoalList: List<GoalData>
):DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldGoalList.size
     }


    override fun getNewListSize(): Int {
        return newGoalList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldGoalList[oldItemPosition] === newGoalList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldGoalList[oldItemPosition].id == newGoalList[newItemPosition].id
         &&  oldGoalList[oldItemPosition].title == newGoalList[newItemPosition].title
         &&  oldGoalList[oldItemPosition].period == newGoalList[newItemPosition].period
     &&  oldGoalList[oldItemPosition].description == newGoalList[newItemPosition].description



    }
}