package com.myproject.trello.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.myproject.trello.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : Base_Activity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportactionbar()
        nav_view.setNavigationItemSelectedListener(this)
    }
//top action bar
    fun supportactionbar() {
        setSupportActionBar(toolbar_main_activity)
        toolbar_main_activity.setNavigationIcon(R.drawable.ic_action_navigation_menu)
        toolbar_main_activity.setNavigationOnClickListener {
           toggledrawer()
        }

    }
//toggle drawer toggles the hamburger icon to open and vice-versa
    fun toggledrawer() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }
//on back pressedvexits out of the activity
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            doublebacktoexit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_my_profile -> {
                Toast.makeText(this, "my profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_sign_out -> {
             FirebaseAuth.getInstance().signOut()//sign out from the application
              /*  flag_activity_clear_top If the activity being started is already running in the current task,
                then instead of launching a new instance of that activity, all of the other activities on top of it are destroyed
                and this intent is delivered to the resumed instance of the activity (now on top), through onNewIntent() ).*/
              /*  When the intent that launches an activity contains the FLAG_ACTIVITY_NEW_TASK flag.
                A new activity is, by default, launched into the task of the activity that called startActivity().
                It's pushed onto the same back stack as the caller. However, if the intent passed to startActivity()
                contains the FLAG_ACTIVITY_NEW_TASK flag, the system looks for a different task to house the new activity.
                Often, it's a new task.*/
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
      drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
