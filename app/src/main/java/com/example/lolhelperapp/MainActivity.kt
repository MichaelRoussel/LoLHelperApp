package com.example.lolhelperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //authentication providers - only email for our app
        val providers = arrayListOf(
            AuthUI.IdpConfig
        )
    }
}
