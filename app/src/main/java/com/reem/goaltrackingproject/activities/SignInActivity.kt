package com.reem.goaltrackingproject.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.reem.goaltrackingproject.MainActivity
import com.reem.goaltrackingproject.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {

    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setupActionBar()

        btn_sign_in.setOnClickListener {
            signInRegisteredUser()
        }
    }


    private fun signInRegisteredUser(){

        val email : String = et_email_signIn.text.toString().trim {it <= ' '}
        val password : String = et_password_signIn.text.toString().trim { it <= ' '}

        if (validateForm(email, password)){
            showProgressDialog(resources.getString(R.string.please_wait))

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                        task -> hideProgressDialog()
                    if (task.isSuccessful){
                        Log.d("SignIn","SignInWithEmailAndPassword")
                        val user = auth.currentUser
                        // go main activity
                        startActivity(Intent(this, MainActivity::class.java))
                    } else{
                        Log.w("SignIn","signInWithEmailAndPassword",task.exception)
                        Toast.makeText(baseContext,"Authentication failed.", Toast.LENGTH_SHORT).show()

                    }
                }

        }

    }




    private fun setupActionBar() {

        setSupportActionBar(toolbar_sign_in_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back)
        }

        toolbar_sign_in_activity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun validateForm( email: String, password: String): Boolean {

        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
                false
            }
            else -> {
                true
            }
        }
    }
}