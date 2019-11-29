package com.example.lolhelperapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.analytics.FirebaseAnalytics


class SignInActivity : AppCompatActivity() {

    val RC_SIGN_IN = 1
    var db = FirebaseFirestore.getInstance()
    var ref = db.document("Users/user")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //authentication providers -  email and google for our app - code from firebase doc
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())
        // create sign-in intent and launch it
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.LoginTheme)
                .build(),
            RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        super.onActivityResult(requestCode, resultCode, data)
        //var email = ""
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                firebaseAnalytics.setUserProperty("champTwo", "")
                firebaseAnalytics.setUserProperty("champOne", "")
                // redirect to landing activity
                val intent = Intent(applicationContext, LandingActivity::class.java)
                startActivity(intent)
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
