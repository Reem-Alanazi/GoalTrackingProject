package com.reem.goaltrackingproject.data

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.reem.goaltrackingproject.Constants
import com.reem.goaltrackingproject.activities.SignUpActivity
import com.reem.goaltrackingproject.fragments.ProfileFragment

class RemoteDataSource {

    private val db = FirebaseFirestore.getInstance()


    fun registerUser(activity: SignUpActivity, userInfo: User) {

        db.collection(Constants.USERS)

            .document(getCurrentUserId()).set(userInfo, SetOptions.merge())


            .addOnSuccessListener {

                activity.userRegisteredSuccess()

            }.addOnFailureListener { e ->

                Log.e(activity.javaClass.simpleName, "Error")

            }
    }


    // or load user data
    fun sentUserData(fragment: Fragment) {

        // so i do not need change it all class just on firestore class
        db.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                // which class i want object which is user class
                val loggedInUser = document.toObject(User::class.java)!!

                when (fragment) {
                    is ProfileFragment -> {
                        fragment.setUserDataInUI(loggedInUser)
                    }

                }

            }.addOnFailureListener { e ->

            }
        Log.e(fragment.javaClass.simpleName, "Error Writing document")

    }


    // check
    fun getCurrentUserId(): String {

        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {

            currentUserID = currentUser.uid
        }

        return currentUserID

    }

}




