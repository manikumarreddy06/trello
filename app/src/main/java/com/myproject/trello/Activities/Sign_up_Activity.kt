package com.myproject.trello.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.myproject.trello.Firebase.Firestore
import com.myproject.trello.R
import com.myproject.trello.models.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class Sign_up_Activity : Base_Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        supportactionbar()

        toolbar_sign_up_activity.setNavigationOnClickListener{ onBackPressed()
        }


        btn_sign_up.setOnClickListener {
            registeruser()
        }
    }
    fun userregisteredsuccess(){
        Toast.makeText(this,"you have successfully created a account with the email" +
                "",Toast.LENGTH_SHORT).show()
        hideprogressdialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }

//set support action bar
    fun supportactionbar(){
    setSupportActionBar(toolbar_sign_up_activity)
    val actionbar=supportActionBar
    if (actionbar!=null){
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
    }
    }

   //fun to register a user
    private fun registeruser(){
        val name:String=et_name.text.toString().trim{it<= ' '}
        val email:String=et_email.text.toString().trim{it<= ' '}
        val password:String=et_password.text.toString().trim{it<= ' '}
       if (validateform(name, email, password)){
           showprogressdialog("loading")
           FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
               if (task.isSuccessful) {
                   val firebaseUser: FirebaseUser = task.result!!.user!!
                   val registeredemail=firebaseUser.email!!
                   val user= User(firebaseUser.uid,name,registeredemail)
                   Firestore().registeruser(this,user)
                   //finish()
               }else{
                   Toast.makeText(this,"registration failed",Toast.LENGTH_SHORT).show()
               }
           }
       }

        }
    //fun to validate user details for signup activity
    //form validation
    private fun validateform( name:String,email:String,password:String):Boolean{
        return when{
            TextUtils.isEmpty(name)->{
                showsnackbarerror("please enter your name")
                false
            }
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


