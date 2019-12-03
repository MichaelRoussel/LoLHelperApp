package com.example.lolhelperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_champion.*

class ChampionActivity : AppCompatActivity() {

    //create db instance to grab info
    val db = FirebaseFirestore.getInstance()

    override fun onStart() {
        super.onStart()

        //make sure there is an authenticated user on start, if not redirect to signin
        val user = FirebaseAuth.getInstance().currentUser
        val storage = FirebaseStorage.getInstance()
        if (user == null) {
            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }

        //load the champion info of the current champion selected
        var champName = intent.getStringExtra("champName")
        val championRef = db.collection("Champions").whereEqualTo("name", champName)
        championRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if (document.exists()) {
                        //populate name, image, winrate and role
                        val championName = document.get("name").toString()
                        val winRate = document.get("winRate").toString()
                        val role = document.get("role").toString()
                        val imageUrl = document.get("imageUrl").toString()
                        val imageRef = storage.getReferenceFromUrl(imageUrl)
                        GlideApp.with(this)
                            .load(imageUrl)
                            .apply(RequestOptions().override(256, 256))
                            .into(championImageView)
                        roleText.text = role
                        champNameText.text = championName
                        winrateText.text = "Winrate: $winRate%"

                        //populate items from database
                        db.collection("Items")
                            .whereEqualTo("name", document.get("itemOne"))
                            .get()
                            .addOnSuccessListener { items ->
                                for(item in items){
                                    val itemName = item.get("name").toString()
                                    val itemImage = item.get("imageUrl").toString()
                                    itemOneText.text = itemName
                                    GlideApp.with(this)
                                        .load(itemImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(itemOneImage)
                                }
                            }

                        db.collection("Items")
                            .whereEqualTo("name", document.get("itemTwo"))
                            .get()
                            .addOnSuccessListener { items ->
                                for(item in items){
                                    val itemName = item.get("name").toString()
                                    val itemImage = item.get("imageUrl").toString()
                                    itemTwoText.text = itemName
                                    GlideApp.with(this)
                                        .load(itemImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(itemTwoImage)
                                }
                            }

                        db.collection("Items")
                            .whereEqualTo("name", document.get("itemThree"))
                            .get()
                            .addOnSuccessListener { items ->
                                for(item in items){
                                    val itemName = item.get("name").toString()
                                    val itemImage = item.get("imageUrl").toString()
                                    itemThreeText.text = itemName
                                    GlideApp.with(this)
                                        .load(itemImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(itemThreeImage)
                                }
                            }

                        db.collection("Items")
                            .whereEqualTo("name", document.get("itemFour"))
                            .get()
                            .addOnSuccessListener { items ->
                                for(item in items){
                                    val itemName = item.get("name").toString()
                                    val itemImage = item.get("imageUrl").toString()
                                    itemFourText.text = itemName
                                    GlideApp.with(this)
                                        .load(itemImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(itemFourImage)
                                }
                            }

                        db.collection("Items")
                            .whereEqualTo("name", document.get("itemFive"))
                            .get()
                            .addOnSuccessListener { items ->
                                for(item in items){
                                    val itemName = item.get("name").toString()
                                    val itemImage = item.get("imageUrl").toString()
                                    itemFiveText.text = itemName
                                    GlideApp.with(this)
                                        .load(itemImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(itemFiveImage)
                                }
                            }

                        db.collection("Items")
                            .whereEqualTo("name", document.get("itemSix"))
                            .get()
                            .addOnSuccessListener { items ->
                                for(item in items){
                                    val itemName = item.get("name").toString()
                                    val itemImage = item.get("imageUrl").toString()
                                    itemSixText.text = itemName
                                    GlideApp.with(this)
                                        .load(itemImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(itemSixImage)
                                }
                            }

                        //load keystone and secondary runes
                        db.collection("Keystones")
                            .whereEqualTo("name", document.get("keystoneRune"))
                            .get()
                            .addOnSuccessListener { runes ->
                                for(rune in runes){
                                    val runeName = rune.get("name").toString()
                                    val runeImage = rune.get("imageUrl").toString()
                                    runeOneText.text = runeName
                                    GlideApp.with(this)
                                        .load(runeImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(runeOneImage)
                                }
                            }

                        db.collection("BasicRunes")
                            .whereEqualTo("name", document.get("runeOne"))
                            .get()
                            .addOnSuccessListener { runes ->
                                for(rune in runes){
                                    val runeName = rune.get("name").toString()
                                    val runeImage = rune.get("imageUrl").toString()
                                    runeTwoText.text = runeName
                                    GlideApp.with(this)
                                        .load(runeImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(runeTwoImage)
                                }
                            }

                        db.collection("BasicRunes")
                            .whereEqualTo("name", document.get("runeTwo"))
                            .get()
                            .addOnSuccessListener { runes ->
                                for(rune in runes){
                                    val runeName = rune.get("name").toString()
                                    val runeImage = rune.get("imageUrl").toString()
                                    runeThreeText.text = runeName
                                    GlideApp.with(this)
                                        .load(runeImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(runeThreeImage)
                                }
                            }

                        db.collection("BasicRunes")
                            .whereEqualTo("name", document.get("runeThree"))
                            .get()
                            .addOnSuccessListener { runes ->
                                for(rune in runes){
                                    val runeName = rune.get("name").toString()
                                    val runeImage = rune.get("imageUrl").toString()
                                    runeFourText.text = runeName
                                    GlideApp.with(this)
                                        .load(runeImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(runeFourImage)
                                }
                            }

                        db.collection("BasicRunes")
                            .whereEqualTo("name", document.get("runeFour"))
                            .get()
                            .addOnSuccessListener { runes ->
                                for(rune in runes){
                                    val runeName = rune.get("name").toString()
                                    val runeImage = rune.get("imageUrl").toString()
                                    runeFiveText.text = runeName
                                    GlideApp.with(this)
                                        .load(runeImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(runeFiveImage)
                                }
                            }

                        db.collection("BasicRunes")
                            .whereEqualTo("name", document.get("runeFive"))
                            .get()
                            .addOnSuccessListener { runes ->
                                for(rune in runes){
                                    val runeName = rune.get("name").toString()
                                    val runeImage = rune.get("imageUrl").toString()
                                    runeSixText.text = runeName
                                    GlideApp.with(this)
                                        .load(runeImage)
                                        .apply(RequestOptions().override(80, 80))
                                        .into(runeSixImage)
                                }
                            }
                    }
                    else {
                        Toast.makeText(this, "No champion found", Toast.LENGTH_LONG).show()
                    }
                }
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
        fabHomeChampion.setOnClickListener{
            val intent = Intent(applicationContext, LandingActivity::class.java)
            startActivity(intent)
        }


    }
}
