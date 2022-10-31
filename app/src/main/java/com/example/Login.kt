package com.example


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.todaydrawings.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private var _binding: Login? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    lateinit var signupBtn: TextView //회원가입 버튼
    lateinit var loginBtn: AppCompatButton //로그인 버튼
    lateinit var email: EditText //이메일
    lateinit var password: EditText //비번


    @SuppressLint("MissingInflatedId")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= Firebase.auth
        //회원가입 버튼 기능//
        signupBtn = findViewById(R.id.signUp)
        //회원가입 버튼 클릭시, 회원가입 페이지로 이동
        signupBtn.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        //id연결 할때는 xml에 설정했던 아이디로
        //xml의 이메일 아이디 연결
        email = findViewById(R.id.editEmail)

        //xml의 비번 아이디 연결
        password = findViewById(R.id.editPassword)


        loginBtn=findViewById(R.id.loginbutton)
        //로그인 버튼 기능//
        loginBtn.setOnClickListener {
            signIn(email.text.toString(), password.text.toString())
        }

    }//함수는 oncreate()밖에 선언해야한다

    private fun signIn(email: String, password: String) {
        //로그인 함수
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //로그인 처리를 해주면 됨!
                    Toast.makeText(this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                    //'메인액티비티'로 이동
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    // 오류가 난 경우!
                    Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_LONG).show()
                }
            }


    }


}

