package com.reem.goaltrackingproject.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.reem.goaltrackingproject.R
import kotlinx.android.synthetic.main.fragment_list_goal.view.*


class ListGoalFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_goal, container, false)

        view.floatingActionButton.setOnClickListener {
         findNavController().navigate(R.id.action_listGoalFragment_to_addGoalFragment)
        }

        return view
    }

}