package com.reem.goaltrackingproject.fragments.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.reem.goaltrackingproject.R


class AddGoalFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //set up menu

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_goal, container, false)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_goal_fragment_menu, menu)
    }


}