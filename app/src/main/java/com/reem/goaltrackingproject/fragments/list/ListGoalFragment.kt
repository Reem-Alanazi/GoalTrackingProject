package com.reem.goaltrackingproject.fragments.list

import android.os.Bundle
import android.view.*
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

}