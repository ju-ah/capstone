package com.example.vellog_login


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.R
import com.example.capstone.main
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private var _binding: Login? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    lateinit var signupBtn: TextView //회원가입 버튼
    lateinit var loginBtn: Button //로그인 버튼
    lateinit var email: EditText //이메일
    lateinit var password: EditText //비번


    @SuppressLint("MissingInflatedId")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
        password = findViewById(R.id.ediPassword)


        //로그인 버튼 기능//
        loginBtn.setOnClickListener {
            createUser(email.text.toString(), password.text.toString())
        }

    }//함수는 oncreate()밖에 선언해야한다

    private fun createUser(email: String, password: String) {
        //로그인 함수
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //로그인 처리를 해주면 됨!
                    Toast.makeText(this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                    //인텐트 메인으로

                    // ToMainActivity()
                } else {
                    // 오류가 난 경우!
                    Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_LONG).show()
                }
            }


    }

//private fun ToMainActivity() {
 //val intent=Intent(this, main.java)
 //startActivity(intent)
  //  }

}

