package com.example

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.todaydrawings.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class GallaryOpen : AppCompatActivity() {

    private var _binding: GallaryOpen? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    lateinit var gallaryBtn: ExtendedFloatingActionButton    //글쓰기버튼, 즉 갤러리 열리는 버튼

    @SuppressLint("MissingInflatedId")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.app_bar_main)

        auth = Firebase.auth
        //글쓰기 버튼 눌러서 갤러리 오픈//
        gallaryBtn = findViewById(R.id.signUp)
        //기능
        gallaryBtn.setOnClickListener {
            val intent = Intent(this, GallaryCollect::class.java)
            startActivity(intent)
        }
    }
}

class GallaryCollect {

}
