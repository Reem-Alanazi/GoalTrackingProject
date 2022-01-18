package com.reem.goaltrackingproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.airbnb.lottie.L
import com.reem.goaltrackingproject.data.GoalData
import com.reem.goaltrackingproject.data.GoalLocalDatabase
import com.reem.goaltrackingproject.data.GoalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// extend android view model

class GoalViewModel(application: Application):AndroidViewModel(application) {

    private val goalDao = GoalLocalDatabase.getDatabase(application).goalDao()
    private val repository : GoalRepository

       // this val will holds live data object
     val getAllData:LiveData<List<GoalData>>

    init {
        repository = GoalRepository(goalDao)
        getAllData=repository.getAllData
    }

    // run of  this fun in background thread
    fun insertData(goalData: GoalData){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(goalData)
        }
    }

    fun updateDate(goalData: GoalData){
        viewModelScope.launch(Dispatchers.IO){
           repository.updateData(goalData)
        }
    }

    fun deleteItem(goalData: GoalData) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(goalData)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()

        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<GoalData>>{
        return repository.searchDatabase(searchQuery)
    }
}