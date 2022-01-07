package com.reem.goaltrackingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.reem.goaltrackingproject.data.RemoteDataSource

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            //auto login
            var currentUserID = RemoteDataSource().getCurrentUserId()


            // if user already have account directly home\main page
            if (currentUserID.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))

            }else{

                startActivity(Intent(this, RegisterActivity::class.java))}
            finish()
        }, 2500)
    }

}
