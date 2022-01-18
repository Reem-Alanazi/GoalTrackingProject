package com.reem.goaltrackingproject.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
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
import com.google.android.material.snackbar.Snackbar
import com.reem.goaltrackingproject.data.GoalData
import com.reem.goaltrackingproject.databinding.FragmentListGoalBinding
import com.reem.goaltrackingproject.viewmodel.SharedViewModel
import jp.wasabeef.recyclerview.animators.LandingAnimator
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import java.text.FieldPosition

class ListGoalFragment : Fragment(),SearchView.OnQueryTextListener {

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
        // Animation RecycleView
        recyclerView.itemAnimator = LandingAnimator().apply {
            addDuration = 300
        }

        // swipe to delete
        swipeToDelete(recyclerView)


    }

    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = adapter.goalDataList[viewHolder.adapterPosition]
                // Deleted Item
                mGoalViewModel.deleteItem(deletedItem)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)

                // Restore Deleted Item
                restoreDeletedGoal(viewHolder.itemView,deletedItem,viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedGoal(view: View, deletedItem: GoalData, position: Int){
        val snackBar = Snackbar.make(
            view,"Deleted '${deletedItem.title}",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Undo"){
            mGoalViewModel.insertData(deletedItem)
            adapter.notifyItemChanged(position)
        }
        snackBar.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_goal_fragment_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_all){
            confirmDeleteAllItem()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!=null){
            searchInGoalDatabase(query)
        }

        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        searchInGoalDatabase(query)

        return true
    }

    private fun searchInGoalDatabase(goal: String) {
        // %$searchQuery% Finds any values that have same "searchQuery" in any position
        val searchQuery= "%$goal%"

        mGoalViewModel.searchDatabase(searchQuery).observe(this, Observer { list ->
           list?.let {
              adapter.setGoalData(it)
           }
        })
    }

    // show Dialog to confirm Delete All Item
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

