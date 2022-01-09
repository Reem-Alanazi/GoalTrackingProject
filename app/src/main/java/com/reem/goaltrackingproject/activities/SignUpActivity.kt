package com.reem.goaltrackingproject.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.reem.goaltrackingproject.R
import com.reem.goaltrackingproject.data.RemoteDataSource
import com.reem.goaltrackingproject.data.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setupActionBar()

        btn_sign_up.setOnClickListener{
            registerUser()
        }

    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_sign_up_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back)
        }

        toolbar_sign_up_activity.setNavigationOnClickListener { onBackPressed() }
    }


    fun userRegisteredSuccess(){
        Toast.makeText(
            this, "You have successfully registered.",
            Toast.LENGTH_SHORT
        ).show()
        hideProgressDialog()
        /*
         Here the new user registered is automatically signed-in so I just signOut the user from firebase
         and send him to registered activity for SignIn */
        FirebaseAuth.getInstance().signOut()
        finish()


    }


    private fun registerUser(){
        val name: String = et_name.text.toString().trim { it <= ' ' }
        val email: String = et_email.text.toString().trim { it <= ' ' }
        val password: String = et_password.text.toString().trim { it <= ' ' }

        if (validateForm(name, email, password)) {
            // show wait process
            showProgressDialog(resources.getString(R.string.please_wait))

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                hideProgressDialog()

                if (task.isSuccessful) {

                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredByEmail = firebaseUser.email!!
                    val user = User(firebaseUser.uid,name, email)
                    RemoteDataSource().registerUser(this, user)

                    Toast.makeText(
                        this,
                        "$name You have successfully registered with email $registeredByEmail",
                        Toast.LENGTH_SHORT
                    ).show()
                    FirebaseAuth.getInstance().signOut()
                    finish()
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter name.")
                false
            }
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