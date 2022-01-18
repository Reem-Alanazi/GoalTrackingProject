package com.reem.goaltrackingproject.fragments

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
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

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View,emptyDatabase : MutableLiveData<Boolean>){
            when(emptyDatabase.value){
               true  -> view.visibility = View.VISIBLE
               false -> view.visibility = View.INVISIBLE
            }
        }

    }

}