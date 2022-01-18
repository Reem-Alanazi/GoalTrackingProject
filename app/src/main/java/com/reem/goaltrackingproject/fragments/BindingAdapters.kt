package com.reem.goaltrackingproject.fragments

import android.view.View
import com.reem.goaltrackingproject.data.Period
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.data.GoalData
import com.reem.goaltrackingproject.fragments.list.ListGoalFragmentDirections

class BindingAdapters  {

    // all custom adapter for project
    companion object{
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view : FloatingActionButton , navigate:Boolean){
            view.setOnClickListener {
                if(navigate){
                    view.findNavController().navigate(R.id.action_listGoalFragment_to_addGoalFragment)
                }
            }
        }

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View,emptyDatabase : MutableLiveData<Boolean>){
            when(emptyDatabase.value){
               true  -> view.visibility = View.VISIBLE
               false -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePeriodColor")
        @JvmStatic
        fun paresPeriodColor(cardView: CardView, period :Period){
            when(period){
            Period.DAY   -> {cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red))}
            Period.WEEK  -> {cardView.setCardBackgroundColor(cardView.context.getColor(R.color.blue))}
            Period.MONTH -> {cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))}
            Period.YEAR  -> {cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))}

            }
        }

        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(view: ConstraintLayout , currentItem: GoalData){
            view.setOnClickListener {
            val action = ListGoalFragmentDirections.actionListGoalFragmentToUpdateGoalFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }

    }

}