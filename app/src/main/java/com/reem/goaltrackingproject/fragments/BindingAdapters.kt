package com.reem.goaltrackingproject.fragments

import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.reem.goaltrackingproject.R

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

    }

}