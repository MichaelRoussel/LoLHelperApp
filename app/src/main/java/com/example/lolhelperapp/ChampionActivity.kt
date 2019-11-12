package com.example.lolhelperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_champion.*

class ChampionActivity : AppCompatActivity() {

    //create db instance to grab info
    val db = FirebaseFirestore.getInstance()

    override fun onStart() {
        super.onStart()

        //make sure there is an authenticated user on start, if not redirect to signin
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champion)

        //sign out button
        fabExitChampion.setOnClickListener{
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    //redirect to sign in after logging out
                    val intent = Intent(applicationContext, SignInActivity::class.java)
                    startActivity(intent)
                }
        }

        //profile button
        fabProfile.setOnClickListener{
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
