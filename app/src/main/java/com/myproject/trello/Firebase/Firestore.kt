package com.myproject.trello.Firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.myproject.trello.Activities.SignIn_Activity
import com.myproject.trello.Activities.Sign_up_Activity
import com.myproject.trello.models.User
import com.myproject.trello.utils.Constants

class Firestore {
    //Firestore instantiated
    private var mfirestore=FirebaseFirestore.getInstance()

    //fun to register user info into firestore data base in signup Activity

    fun registeruser(activity:Sign_up_Activity,userinfo: User){
        //collection is a list of individual documents

        mfirestore.collection(Constants.Users)//name of firestore collection
            //A dcoument is a collection of data like string ,float ,another document
            .document(getcurrentuserid())
            .set(userinfo,
                SetOptions.merge())
            .addOnSuccessListener {
                    activity.userregisteredsuccess()
            }.addOnFailureListener {
                e->
                Log.e(activity.javaClass.simpleName,"error")
            }
    }
    fun signinuser(activity:SignIn_Activity){
        mfirestore.collection(Constants.Users)//name of firestore collection
            //A dcoument is a collection of data like string ,float ,another document
            .document(getcurrentuserid())
            .get()
            .addOnSuccessListener {document->
               val loggedinuser=document.toObject(User::class.java)
                if (loggedinuser != null) {
                    activity.signinsuccess(loggedinuser)
                }
            }.addOnFailureListener {
                    e->
                Log.e("signin user".javaClass.simpleName,"error")
            }
    }
    fun getcurrentuserid():String{
        var currentuser=FirebaseAuth.getInstance().currentUser
        var currentuserid=""
        if (currentuser!=null){
            currentuserid=currentuser.uid
        }
        return currentuserid
    }

}
