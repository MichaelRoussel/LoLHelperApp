package com.example.lolhelperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_champion.*
import kotlinx.android.synthetic.main.profile_activity.*

class   ProfileActivity : AppCompatActivity() {

    //create db instance to grab users and other info
    val db = FirebaseFirestore.getInstance()

//    override fun onStart() {
//        super.onStart()
//
//        //make sure there is an authenticated user on start, if not redirect to signin
//        val user = FirebaseAuth.getInstance().currentUser
//        if (user == null) {
//            val intent = Intent(applicationContext, SignInActivity::class.java)
//            startActivity(intent)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val email = user.email
            textViewEmail.text = email
        }

        val docRef = db.collection("Users").document(user?.uid.toString())
        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    val userObject = documentSnapshot.toObject(User::class.java)
                    champOneText.text = userObject?.preferredChampOne
                    textView5.text = userObject?.preferredChampTwo
                    textView7.text = userObject?.preferredChampThree
                    textView9.text = userObject?.preferredChampFour
                }
            }


        //sign out button
        fabExitProfile.setOnClickListener{
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    //redirect to sign in after logging out
                    val intent = Intent(applicationContext, SignInActivity::class.java)
                    startActivity(intent)
                }
        }

        //back button
        fabHome.setOnClickListener{
            val intent = Intent(applicationContext, LandingActivity::class.java)
            startActivity(intent)
        }

        clear.setOnClickListener {
            val tbl = db.collection("Users")
            val user = FirebaseAuth.getInstance().currentUser
            val docRef = db.collection("Users").document(user?.uid.toString())
            docRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot != null) {
                        val userObject = documentSnapshot.toObject(User::class.java)
                        val id = user?.uid.toString()
                        val email = user?.email.toString()
                        val preferredOne = ""
                        val preferredTwo = ""
                        val preferredThree = ""
                        val preferredFour = ""
                        val userObjectTwo = User(id, email, preferredOne, preferredTwo, preferredThree, preferredFour)
                        tbl.document(id).set(userObjectTwo)
                        Toast.makeText(this, "Champions Cleared", Toast.LENGTH_LONG).show()
                        champOneText.text = ""
                        textView5.text = ""
                        textView7.text = ""
                        textView9.text = ""

                    }
                }
        }

    }
}