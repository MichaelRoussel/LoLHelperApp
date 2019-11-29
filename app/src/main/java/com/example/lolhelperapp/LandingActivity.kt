package com.example.lolhelperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity() {

    //create db instance to grab users and other info
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
        setContentView(R.layout.activity_landing)


        //sign out button
        fabExitLanding.setOnClickListener{
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

        //button to champion page
        champButtonOne.setOnClickListener{
            val intent = Intent(applicationContext, ChampionActivity::class.java)
            startActivity(intent)
        }

        //add champions button
        buttonSave.setOnClickListener{
            //save the input
            var champion = editTextAdd.text.toString().trim()
            //validate it to ensure it's not empty
            if(TextUtils.isEmpty(champion)) {
                Toast.makeText(this, "Please enter a Champion.", Toast.LENGTH_LONG).show()
            }
            else {
                //store variable in user object
                val tbl = db.collection("Users")
                val user = FirebaseAuth.getInstance().currentUser
                val docRef = db.collection("Users").document(user?.uid.toString())
                docRef.get()
                    .addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot != null) {
                            val userObject = documentSnapshot.toObject(User::class.java)
                            val id = user?.uid.toString()
                            val email = user?.email.toString()
                            val perferedOne = userObject?.preferredChampOne
                            val perferedTwo = userObject?.preferredChampTwo
                            val perferedThree = userObject?.preferredChampThree
                            val perferedFour = userObject?.preferredChampFour
                            if(perferedOne == null || perferedOne.isEmpty()){
                                val userObjectTwo = User(id, email, champion, perferedTwo, perferedThree, perferedFour)
                                tbl.document(id).set(userObjectTwo)
                                Toast.makeText(this, "Champion added", Toast.LENGTH_LONG).show()
                            }
                            else{
                                if(perferedTwo == null || perferedTwo.isEmpty()){
                                    val userObjectTwo = User(id, email, perferedOne, champion, perferedThree, perferedFour)
                                    tbl.document(id).set(userObjectTwo)
                                    Toast.makeText(this, "Champion added", Toast.LENGTH_LONG).show()
                                }
                                else{
                                    if(perferedThree == null || perferedThree.isEmpty()){
                                        val userObjectTwo = User(id, email, perferedOne, perferedTwo, champion, perferedFour)
                                        tbl.document(id).set(userObjectTwo)
                                        Toast.makeText(this, "Champion added", Toast.LENGTH_LONG).show()
                                    }
                                    else{
                                        if(perferedFour == null || perferedFour.isEmpty()){
                                            val userObjectTwo = User(id, email, perferedOne, perferedTwo, perferedThree, champion)
                                            tbl.document(id).set(userObjectTwo)
                                            Toast.makeText(this, "Champion added", Toast.LENGTH_LONG).show()
                                        }
                                        else{
                                            Toast.makeText(this, "Please make room on the profile page!", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            }

                        }
                    }
            }
        }

    }
}