package com.myproject.trello.Activities

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.myproject.trello.R
import kotlinx.android.synthetic.main.dialog_progress.*


open class Base_Activity : AppCompatActivity() {

    private var doublebackbuttonpressesonce=false
    private lateinit var mprogressdialog:Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)


    }

    // fun progress Dialog
   fun showprogressdialog(text:String){
        mprogressdialog= Dialog(this@Base_Activity)
        //Set Title Of Progress Dialog
      mprogressdialog.setContentView(R.layout.dialog_progress)

        //Set Message Of Progress Dialog
      mprogressdialog.tv_progress_text.text=text

        //Show  Progress Dialog
        mprogressdialog.show()

    }
    fun hideprogressdialog(){
        mprogressdialog.dismiss()
    }
    fun getcurrentuserid():String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
    fun doublebacktoexit(){

            if (doublebackbuttonpressesonce) {
                super.onBackPressed()
                return
            }
            this.doublebackbuttonpressesonce = true
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doublebackbuttonpressesonce = false }, 2000)
    }
    fun showsnackbarerror(message: String){
           val  snackBar=Snackbar.make(findViewById(android.R.id.content)
               ,message,Snackbar.LENGTH_LONG)
        val Snackbarview=snackBar.view
       Snackbarview.setBackgroundColor(ContextCompat.getColor(this,R.color.snackbar_error_color))
        snackBar.show()    }
}