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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel


        // Set up recyclerview
        setUpRecyclerview()

        // Observer LiveData
        mGoalViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setGoalData(data)
        })


        // Set up menu
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setUpRecyclerview() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())


        // swipe to delete
        swipeToDelete(recyclerView)


    }

    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = adapter.goalDataList[viewHolder.adapterPosition]
                mGoalViewModel.deleteItem(itemToDelete)
                Toast.makeText(requireContext(),"Successfully removed '${itemToDelete.title}'",
                    Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
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

     // for avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

