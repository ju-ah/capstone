package com.example


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todaydrawings.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {
    lateinit var back: TextView
    lateinit var nickname: EditText
    lateinit var email: EditText
    lateinit var pw: EditText
    lateinit var pw2: EditText
    lateinit var pwcheck: Button
    lateinit var signupBtn: Button
    private lateinit var auth: FirebaseAuth

    private var _binding: Login? = null
    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Firebase auth 초기화하기
        auth= Firebase.auth
        //뒤로 가기 버튼
        back = findViewById(R.id.back)
        back.setOnClickListener { this.onBackPressed() }

        //기입 항목
        nickname = findViewById(R.id.signNick)
        email = findViewById(R.id.signmail)
        pw = findViewById(R.id.signPW)
        pw2 = findViewById(R.id.signPW2)


        //비밀번호 확인용 버튼
        pwcheck = findViewById(R.id.pwcheckbutton)
        pwcheck.setOnClickListener { v: View? ->
            if (pw.text.toString() == pw2.text.toString()) {
                pwcheck.text = "일치"
            } else {
                Toast.makeText(this@Signup, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
            }
        }

        //회원가입 완료 버튼
        signupBtn = findViewById(R.id.signupbutton)

        //회원가입 완료 버튼을 누르면
        signupBtn.setOnClickListener { v: View? ->
            signUp(
                email.text.toString(),
                pw.text.toString(),
                pw2.text.toString(),
                nickname.text.toString()
            )
        }
    }

    private fun signUp(email: String, pw: String, pw2: String, nickname: String) {
        auth.createUserWithEmailAndPassword(email, pw)
            .addOnCompleteListener(this) { task ->
                //입력란 비어있는지 확인
                if (nickname.isEmpty() || email.isEmpty() || pw.isEmpty() || pw2.isEmpty()) {
                    Toast.makeText(this, "입력란을 확인해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show()
                        //로그인 화면 이동
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        // 액티비티 종료
                        finish()
                    }
                    Toast.makeText(this, "회원가입에 실패했습니다!", Toast.LENGTH_SHORT).show()
                    Log.d("signup", task.exception?.message.toString())


                }
            }
    }
}



