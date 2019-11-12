package com.example.lolhelperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.profile_activity.*


class ProfileActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()

    //private var adapter: UserAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        val user = FirebaseAuth.getInstance().currentUser
        var email = ""
        if(user == null){

        }else {
            email = user.email.toString()
        }
        textView.text = email

    }
}