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
import com.google.firebase.auth.FirebaseAuth

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

        //뒤로 가기 버튼
        back = findViewById(R.id.back)
        back.setOnClickListener { onBackPressed() }

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
            .addOnCompleteListener { task ->
                //입력란 비어있는지 확인
                if (nickname.toString().isEmpty() || email.toString()
                        .isEmpty() || pw.toString().isEmpty() || pw2.toString()
                        .isEmpty()
                ) {
                    Toast.makeText(this, "입력란을 확인해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show()
                    //로그인 화면 이동
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    // 액티비티 종료
                    finish()


                }
            }
    }
}



