package com.reem.goaltrackingproject.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.reem.goaltrackingproject.R

class UpdateGoalFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // set up
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_update_goal, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_goal_fragment_menu, menu)
    }

}