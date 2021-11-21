package com.myproject.trello.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.myproject.trello.R
import kotlinx.android.synthetic.main.activity_splash.*

class Splash_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
       window.setFlags(
           WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
       )
        tv_app_name.alpha=0f
        tv_app_name.animate().setDuration(2500).alpha(1f).withEndAction {
            val i=Intent(this, Intro_Activity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

        }


    }
