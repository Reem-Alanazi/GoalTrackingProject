package com.reem.goaltrackingproject.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.data.GoalData
import com.reem.goaltrackingproject.data.Period
import com.reem.goaltrackingproject.viewmodel.GoalViewModel
import com.reem.goaltrackingproject.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_update_goal.*
import kotlinx.android.synthetic.main.fragment_update_goal.view.*
import kotlinx.android.synthetic.main.goal_item.view.*

class UpdateGoalFragment : Fragment() {
     // auto generate by save args
    private val args by navArgs<UpdateGoalFragmentArgs>()
    private val mGoalViewModel : GoalViewModel by activityViewModels()
    private val mSharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_update_goal, container, false)

        setHasOptionsMenu(true)

        view.current_title_et.setText(args.currentGoal.title)
        view.current_description_et.setText(args.currentGoal.description)
        view.current_spinner.setSelection(mSharedViewModel.convertPeriodToInt(args.currentGoal.period))
        view.current_spinner.onItemSelectedListener = mSharedViewModel.listener

        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_goal_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_save -> updateGoal()
            R.id.menu_delete -> confirmItemDelete()
        }
        return super.onOptionsItemSelected(item)
    }

    // update the current item goal
    private fun updateGoal() {
        val title = current_title_et.text.toString()
        val description = current_description_et.text.toString()
        val selectedPeriod = current_spinner.selectedItem.toString()

        val validate = mSharedViewModel.verifyDataFromUser(title, description)
        if (validate){
            val updateGoal = GoalData(args.currentGoal.id,
                 title,
                mSharedViewModel.convertPeriod(selectedPeriod),
                description
            )
            mGoalViewModel.updateDate(updateGoal)
            Toast.makeText(requireContext(),"Successfully updated your goal",Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_updateGoalFragment_to_listGoalFragment)
        }else{
         Toast.makeText(requireContext(),"Please fill all field",Toast.LENGTH_SHORT)
             .show()
        }
    }

    private fun confirmItemDelete() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mGoalViewModel.deleteItem(args.currentGoal)
            Toast.makeText(requireContext(),"Successfully Removed '${args.currentGoal.title}'",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateGoalFragment_to_listGoalFragment)
        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete'${args.currentGoal.title}' ?")
        builder.setMessage("Are you sure you want remove '${args.currentGoal.title}' ?")
        builder.create().show()
    }

}
