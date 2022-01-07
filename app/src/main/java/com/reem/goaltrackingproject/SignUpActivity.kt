package com.reem.goaltrackingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
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
}