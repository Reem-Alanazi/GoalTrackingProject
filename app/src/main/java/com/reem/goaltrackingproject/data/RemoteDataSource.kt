package com.reem.goaltrackingproject.data

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.reem.goaltrackingproject.Constants
import com.reem.goaltrackingproject.SignUpActivity

class RemoteDataSource {

    private val db = FirebaseFirestore.getInstance()




    // check
    fun getCurrentUserId(): String{

        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null ){

            currentUserID = currentUser.uid
        }

        return currentUserID

    }


}