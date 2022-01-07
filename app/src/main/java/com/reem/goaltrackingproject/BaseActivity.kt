package com.reem.goaltrackingproject

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity : AppCompatActivity() {

    private lateinit var myProgressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }


    fun showProgressDialog(text: String) {
        myProgressDialog = Dialog(this)

        /* Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        myProgressDialog.setContentView(R.layout.dialog_progress)

        myProgressDialog.tv_progress_text.text = text

        //Start the dialog and display it on screen.
        myProgressDialog.show()
    }


    fun hideProgressDialog() {
        myProgressDialog.dismiss()
    }


    fun showErrorSnackBar(message: String) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                this@BaseActivity,
                R.color.snackbar_error_color
            )
        )
        snackBar.show()
    }

}

