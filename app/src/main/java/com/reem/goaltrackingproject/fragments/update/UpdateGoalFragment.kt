package com.reem.goaltrackingproject.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.data.Period
import com.reem.goaltrackingproject.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_update_goal.view.*

class UpdateGoalFragment : Fragment() {
     // auto generate by save args
    private val args by navArgs<UpdateGoalFragmentArgs>()

    private val mSharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_update_goal, container, false)

        // set up
        setHasOptionsMenu(true)

        view.current_title_et.setText(args.currentGoal.title)
        view.current_description_et.setText(args.currentGoal.description)
        view.current_description_et.setSelection(parasPeriod(args.currentGoal.period))
        view.current_spinner.onItemSelectedListener = mSharedViewModel.listener

        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_goal_fragment_menu, menu)
    }

    private fun parasPeriod(period: Period):Int{
        return when(period){
            Period.DAY -> 0
            Period.WEEK -> 1
            Period.MONTH -> 2
            Period.YEAR -> 3
        }
    }

}