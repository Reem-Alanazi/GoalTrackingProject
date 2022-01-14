package com.reem.goaltrackingproject.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.viewmodel.GoalViewModel
import kotlinx.android.synthetic.main.fragment_list_goal.view.*
import androidx.appcompat.app.AppCompatActivity





class ListGoalFragment : Fragment() {


    private val mGoalViewModel : GoalViewModel by viewModels()

    private val adapter : ListAdapter by lazy { ListAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_goal, container, false)

        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mGoalViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setGoalData(data)

        })


        view.floatingActionButton.setOnClickListener {
         findNavController().navigate(R.id.action_listGoalFragment_to_addGoalFragment)
        }


        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_goal_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_all){
            confirmDeleteAllItem()
        }
        return super.onOptionsItemSelected(item)
    }
     // show Dialog
    private fun confirmDeleteAllItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mGoalViewModel.deleteAll()
            Toast.makeText(requireContext(),"delete item successfully", Toast.LENGTH_SHORT)
                .show()
        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to remove all goals ?")
        builder.create().show()
    }

}

