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
import com.reem.goaltrackingproject.databinding.FragmentListGoalBinding
import com.reem.goaltrackingproject.viewmodel.SharedViewModel

class ListGoalFragment : Fragment() {

    private val mGoalViewModel : GoalViewModel by viewModels()
    private val mSharedViewModel : SharedViewModel by viewModels()
    private val adapter : ListAdapter by lazy { ListAdapter()}
    private var _binding : FragmentListGoalBinding? = null
    private val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Data binding
        _binding = FragmentListGoalBinding.inflate(inflater, container, false)

        // set up recyclerview
        setUpRecyclerview()

        // Observer LiveData
        mGoalViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setGoalData(data)

        })

        mSharedViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            showEmptyImageDatabase(it)
        })



        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setUpRecyclerview() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())    }

    private fun showEmptyImageDatabase(emptyDatabase : Boolean) {
      if(emptyDatabase){
          view?.no_data_imageView?.visibility = View.VISIBLE
          view?.no_data_textView?.visibility = View.VISIBLE
      }else{
          view?.no_data_imageView?.visibility = View.INVISIBLE
          view?.no_data_textView?.visibility = View.INVISIBLE
      }
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
            Toast.makeText(requireContext(),"Successfully Removed All Goals", Toast.LENGTH_SHORT)
                .show()
        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to remove all goals?")
        builder.create().show()
    }

}

