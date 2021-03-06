package com.myproject.trello.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.myproject.trello.R
import kotlinx.android.synthetic.main.activity_intro.*

class Intro_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        btn_sign_up_intro.setOnClickListener(){
            startActivity(Intent(this, Sign_up_Activity::class.java))
        }
        btn_sign_in_intro.setOnClickListener(){
            startActivity(Intent(this, SignIn_Activity::class.java))
        }
    }
}