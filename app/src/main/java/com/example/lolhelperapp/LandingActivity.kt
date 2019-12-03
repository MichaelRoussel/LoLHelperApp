package com.example.lolhelperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_landing.*
import org.w3c.dom.Text

class LandingActivity : AppCompatActivity() {

    //create db instance to grab users and other info
    val db = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser
    var preferredChampOne = ""
    var preferredChampTwo = ""
    var preferredChampThree= ""
    var preferredChampFour= ""


    override fun onStart() {
        super.onStart()

        //make sure there is an authenticated user on start, if not redirect to signin
        if (user == null) {
            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        } else {
            //store variable in user object
            val user = FirebaseAuth.getInstance().currentUser
            val docRef = db.collection("Users").document(user?.uid.toString())
            docRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot != null) {
                        val userObject = documentSnapshot.toObject(User::class.java)
                        preferredChampOne = userObject?.preferredChampOne.toString()
                        preferredChampTwo = userObject?.preferredChampTwo.toString()
                        preferredChampThree = userObject?.preferredChampThree.toString()
                        preferredChampFour = userObject?.preferredChampFour.toString()
                        loadButtons()
                    }
                }
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



        //add champions button
        buttonSave.setOnClickListener{
            //save the input
            var champion = editTextAdd.text.toString().trim()
            var doesNotExist = false
            var duplicate = false
            //validate it to ensure it's not empty
            if(TextUtils.isEmpty(champion)) {
                Toast.makeText(this, "Please enter a valid Champion.", Toast.LENGTH_LONG).show()
            } else {
                db.collection("Champions").get()
                    .addOnSuccessListener { documents ->
                        for(document in documents) {
                            if(TextUtils.equals(document.get("name").toString(), champion)) {
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
                                                val preferredOne = userObject?.preferredChampOne
                                                val preferredTwo = userObject?.preferredChampTwo
                                                val preferredThree = userObject?.preferredChampThree
                                                val preferredFour = userObject?.preferredChampFour
                                                if(TextUtils.equals(preferredOne, champion) || TextUtils.equals(preferredOne, champion)
                                                    || TextUtils.equals(preferredOne, champion) || TextUtils.equals(preferredOne, champion)) {
                                                    duplicate = true
                                                }
                                                else {
                                                    if (preferredOne == null || preferredOne.isEmpty()) {
                                                        val userObjectTwo = User(
                                                            id,
                                                            email,
                                                            champion,
                                                            preferredTwo,
                                                            preferredThree,
                                                            preferredFour
                                                        )
                                                        tbl.document(id).set(userObjectTwo)
                                                        Toast.makeText(
                                                            this,
                                                            "Champion added",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        loadButtons()
                                                    } else {
                                                        if (preferredTwo == null || preferredTwo.isEmpty()) {
                                                            val userObjectTwo = User(
                                                                id,
                                                                email,
                                                                preferredOne,
                                                                champion,
                                                                preferredThree,
                                                                preferredFour
                                                            )
                                                            tbl.document(id).set(userObjectTwo)
                                                            Toast.makeText(
                                                                this,
                                                                "Champion added",
                                                                Toast.LENGTH_LONG
                                                            ).show()
                                                            loadButtons()
                                                        } else {
                                                            if (preferredThree == null || preferredThree.isEmpty()) {
                                                                val userObjectTwo = User(
                                                                    id,
                                                                    email,
                                                                    preferredOne,
                                                                    preferredTwo,
                                                                    champion,
                                                                    preferredFour
                                                                )
                                                                tbl.document(id).set(userObjectTwo)
                                                                Toast.makeText(
                                                                    this,
                                                                    "Champion added",
                                                                    Toast.LENGTH_LONG
                                                                ).show()
                                                                loadButtons()
                                                            } else {
                                                                if (preferredFour == null || preferredFour.isEmpty()) {
                                                                    val userObjectTwo = User(
                                                                        id,
                                                                        email,
                                                                        preferredOne,
                                                                        preferredTwo,
                                                                        preferredThree,
                                                                        champion
                                                                    )
                                                                    tbl.document(id)
                                                                        .set(userObjectTwo)
                                                                    Toast.makeText(
                                                                        this,
                                                                        "Champion added",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                    loadButtons()
                                                                } else {
                                                                    Toast.makeText(
                                                                        this,
                                                                        "Please make room on the profile page!",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                loadButtons()
                                                if(duplicate) {
                                                    Toast.makeText(this, "This champion is already on your profile.", Toast.LENGTH_LONG).show()
                                                    duplicate = false
                                                }
                                            }
                                        }
                                doesNotExist = false
                                break
                            } else {
                                doesNotExist = true
                            }
                        }
                        if(doesNotExist) {
                            Toast.makeText(this, "Please enter a valid Champion.", Toast.LENGTH_LONG).show()
                            doesNotExist = false
                        }

                    }
            }
        }

    }

    fun loadButtons() {
        val docRef = db.collection("Users").document(user?.uid.toString())
        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    val userObject = documentSnapshot.toObject(User::class.java)
                    val champOne = userObject?.preferredChampOne.toString()
                    val champTwo = userObject?.preferredChampTwo.toString()
                    val champThree = userObject?.preferredChampThree.toString()
                    val champFour = userObject?.preferredChampFour.toString()
                    // populate image buttons with the matching champion image
                    db.collection("Champions").whereEqualTo("name", champOne)
                        .get()
                        .addOnSuccessListener { documents ->
                            for(document in documents) {
                                val imageUrl = document.get("imageUrl")
                                GlideApp.with(this)
                                    .load(imageUrl)
                                    .apply(RequestOptions().override(500, 500))
                                    .into(champButtonOne)
                            }
                        }

                    db.collection("Champions").whereEqualTo("name", champTwo)
                        .get()
                        .addOnSuccessListener { documents ->
                            for(document in documents) {
                                val imageUrl = document.get("imageUrl")
                                GlideApp.with(this)
                                    .load(imageUrl)
                                    .apply(RequestOptions().override(500, 500))
                                    .into(champButtonTwo)
                            }
                        }

                    db.collection("Champions").whereEqualTo("name", champThree)
                        .get()
                        .addOnSuccessListener { documents ->
                            for(document in documents) {
                                val imageUrl = document.get("imageUrl")
                                GlideApp.with(this)
                                    .load(imageUrl)
                                    .apply(RequestOptions().override(500, 500))
                                    .into(champButtonThree)
                            }
                        }

                    db.collection("Champions").whereEqualTo("name", champFour)
                        .get()
                        .addOnSuccessListener { documents ->
                            for(document in documents) {
                                val imageUrl = document.get("imageUrl")
                                GlideApp.with(this)
                                    .load(imageUrl)
                                    .apply(RequestOptions().override(500, 500))
                                    .into(champButtonFour)
                            }
                        }
                    //update the button onClickListeners to point to the correct champion name
                    if(!champOne.isEmpty()) {
                        champButtonOne.setOnClickListener {
                            val intent = Intent(applicationContext, ChampionActivity::class.java)
                            intent.putExtra("champName", champOne)
                            startActivity(intent)
                        }
                    }

                    if(!champTwo.isEmpty()) {
                        champButtonTwo.setOnClickListener {
                            val intent = Intent(applicationContext, ChampionActivity::class.java)
                            intent.putExtra("champName", champTwo)
                            startActivity(intent)
                        }
                    }
                    if(!champThree.isEmpty()) {
                        champButtonThree.setOnClickListener {
                            val intent = Intent(applicationContext, ChampionActivity::class.java)
                            intent.putExtra("champName", champThree)
                            startActivity(intent)
                        }
                    }

                    if(!champFour.isEmpty()) {
                        champButtonFour.setOnClickListener {
                            val intent = Intent(applicationContext, ChampionActivity::class.java)
                            intent.putExtra("champName", champFour)
                            startActivity(intent)
                        }
                    }
                }
            }

    }
}