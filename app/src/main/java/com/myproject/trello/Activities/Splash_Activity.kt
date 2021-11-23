package com.myproject.trello.Activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.myproject.trello.Firebase.Firestore
import com.myproject.trello.R
import kotlinx.android.synthetic.main.activity_splash.*


class Splash_Activity : AppCompatActivity() {

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_splash)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

      /*  // This is used to get the file from the assets folder and set it to the title textView.
        val typeface: Typeface =
            Typeface.createFromAsset(assets, "carbon bl.ttf")
        tv_app_name.typeface = typeface
*/
        // Adding the handler to after the a task after some delay.
        val currentUserID = Firestore().getcurrentuserid()
        tv_app_name.alpha=0f
        tv_app_name.animate().setDuration(3000).alpha(1f).withEndAction{
            if (currentUserID.isNotEmpty()) {
                startActivity(Intent(this, MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }else{
                startActivity(Intent(this, Intro_Activity::class.java))
            }
            finish()
        }


    }
}