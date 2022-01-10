package com.reem.goaltrackingproject.fragments.add

import android.icu.text.CaseMap
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.data.GoalData
import com.reem.goaltrackingproject.data.Period
import com.reem.goaltrackingproject.viewmodel.GoalViewModel
import kotlinx.android.synthetic.main.fragment_add_goal.*


class AddGoalFragment : Fragment() {

    private val mGoalViewModel : GoalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_goal, container, false)

        //set up menu
        setHasOptionsMenu(true)

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_goal_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.menu_add){
            insertDataToDatabase()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val mTitle = title_et.text.toString()
        val mPeriod = period_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()
        // if validation is true i will insert data to database
        val validation = verifyDataFromUser(mTitle,mDescription)
        if (validation){
            val newDataInsert = GoalData(
                0,mTitle,
                convertPeriod(mPeriod), // mPeriod need to convert to string so i use fun pares
                mDescription
            )

            mGoalViewModel.insertData(newDataInsert)
            Toast.makeText(requireContext(),"Successfully added Goal",Toast.LENGTH_SHORT).show()
            // Than navigate back
            findNavController().navigate(R.id.action_addGoalFragment_to_listGoalFragment)
        }else{
            Toast.makeText(requireContext(),"You need to fall all the fields",Toast.LENGTH_SHORT).show()

        }


    }

    // if null or not
    private fun verifyDataFromUser(title: String, description : String ): Boolean{
        return if(TextUtils.isEmpty(title)|| TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty() || description.isEmpty())

    }

    private fun convertPeriod(period : String): Period{
        return when(period){
            "Day" -> (Period.DAY)
            "Week" -> (Period.WEEK)
            "Month" -> (Period.MONTH)
            else -> (Period.YEAR)
        }
    }
}