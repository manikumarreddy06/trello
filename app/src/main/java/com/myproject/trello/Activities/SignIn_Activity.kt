package com.myproject.trello.Activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.myproject.trello.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.et_email
import kotlinx.android.synthetic.main.activity_sign_in.et_password
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignIn_Activity : Base_Activity() {
    private lateinit var auth: FirebaseAuth

    // ...
// Initialize Firebase Auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = Firebase.auth

        supportactionbar()
        toolbar_sign_In_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btn_sign_in.setOnClickListener {
            registeruser()
        }

    }

    fun supportactionbar() {
        setSupportActionBar(toolbar_sign_In_activity)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }
    }
    private fun registeruser(){
        var email:String=et_email.text.toString().trim{it<=' '}
        var password:String=et_password.text.toString().trim{it<=' '}

        if (validatesigninuser(email, password)){
            showprogressdialog("Loading...please wait")
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideprogressdialog()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        startActivity(Intent(this,MainActivity::class.java))
                        // updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }

        }
    }
private fun validatesigninuser(email:String,password:String):Boolean{
return when{
    TextUtils.isEmpty(email)->{
        showsnackbarerror("please enter your email")
        false
    }
    TextUtils.isEmpty(password)->{
        showsnackbarerror("please enter your password")
        false
    }
    else -> true
}

}
}
