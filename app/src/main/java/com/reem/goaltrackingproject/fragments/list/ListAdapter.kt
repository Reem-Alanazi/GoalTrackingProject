package com.reem.goaltrackingproject.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reem.goaltrackingproject.GoalDiffUtil
import com.reem.goaltrackingproject.data.GoalData
import com.reem.goaltrackingproject.databinding.GoalItemBinding


class ListAdapter :RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

     var goalDataList = emptyList<GoalData>()

    class MyViewHolder(private val binding: GoalItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(goalData: GoalData){
            binding.goalItemData = goalData
            binding.executePendingBindings() // run in ui thread
        }

        companion object {
           fun from(parent: ViewGroup): MyViewHolder{
               val layoutInflater = LayoutInflater.from(parent.context)
               val binding = GoalItemBinding.inflate(layoutInflater,parent,false)
               return MyViewHolder(binding)
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = goalDataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = goalDataList.size

    // update event that given to adapter
    fun setGoalData(goalData: List<GoalData>){
        val goalDiffUtil = GoalDiffUtil(goalDataList, goalData)
        val goalDiffResult = DiffUtil.calculateDiff(goalDiffUtil)
        this.goalDataList = goalData
        goalDiffResult.dispatchUpdatesTo(this)

    }
}