package com.reem.goaltrackingproject.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.data.GoalData
import com.reem.goaltrackingproject.data.Period
import kotlinx.android.synthetic.main.goal_item.view.*

class ListAdapter :RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    var goalDataList = emptyList<GoalData>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goal_item , parent , false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.apply{
            title_goal_text.text = goalDataList[position].title
            description_goal_text.text = goalDataList[position].description
            item_background.setOnClickListener {
                val action = ListGoalFragmentDirections.actionListGoalFragmentToUpdateGoalFragment(goalDataList[position])
                findNavController().navigate(action)
            }

        }

        when(goalDataList[position].period){
            Period.DAY -> holder.itemView.period_filter_card.setCardBackgroundColor(
                ContextCompat.getColor(holder.itemView.context,
                R.color.red))
            Period.WEEK -> holder.itemView.period_filter_card.setCardBackgroundColor(
                ContextCompat.getColor(holder.itemView.context,
                    R.color.blue))
            Period.MONTH -> holder.itemView.period_filter_card.setCardBackgroundColor(
                ContextCompat.getColor(holder.itemView.context,
                    R.color.yellow))
            Period.YEAR  -> holder.itemView.period_filter_card.setCardBackgroundColor(
                ContextCompat.getColor(holder.itemView.context,
                    R.color.green))

        }
    }

    override fun getItemCount(): Int = goalDataList.size

    fun setGoalData(goalData: List<GoalData>){
        this.goalDataList = goalData
        notifyDataSetChanged()

    }

}