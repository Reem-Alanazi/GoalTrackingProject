package com.reem.goaltrackingproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.data.RemoteDataSource
import com.reem.goaltrackingproject.data.User
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    companion object {
        //A unique code for asking the Read Storage Permission using
        // this we will be check and identify in the method onRequestPermissionsResult
        private const val READ_STORAGE_PERMISSION_CODE = 1

        private const val PICK_IMAGE_REQUEST_CODE = 2
    }

    private lateinit var mUserDetails : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        RemoteDataSource().sentUserData(this)

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    fun setUserDataInUI(user : User){

        mUserDetails = user
        // Load the user image in the ImageView.
        Glide
            .with(this)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.profile)
            .into(iv_profile_user_image)

        et_name.setText(user.name)
        et_email.setText(user.email)
        if (user.phone != 0L){ // 0L as long value
            et_phone.setText(user.phone.toString())
        }
    }
}