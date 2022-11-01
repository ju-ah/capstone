package com.example.multiimageactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MultiImageAdapter
import com.example.todaydrawings.R
import kotlinx.android.synthetic.main.activity_writing.*
import java.util.ArrayList

class Writing : AppCompatActivity() {
    // 이미지 데이터 리스트
    var list = ArrayList<Uri>()
    val adapter = MultiImageAdapter(list, this)
    lateinit var pick: Button

    @SuppressLint("MissingInflatedId", "IntentReset")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)

        pick = findViewById(R.id.pick)

        pick.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT

            //startActivityForeResult 메소드는 startActivity() 와 다르게 콜백 메소드를 부름
            //두번째 인자값 (200)이 requestCode가 되고 onActivityResult() 에서 받아온 data를 판별해 작업
            startActivityForResult(intent, 200)
        }


        // 리사이클러뷰
        val recyclerview = findViewById<RecyclerView>(R.id.pickedImg)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter

    }


    /*액티비티 호출, 반환할 때*/
    @SuppressLint("NotifyDataSetChanged")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 200) {
            list.clear()

            if (data?.clipData != null) { // 사진 여러개 선택한 경우
                val count = data.clipData!!.itemCount
                if (count > 10) {
                    Toast.makeText(applicationContext, "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG)
                        .show()
                    return
                }
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    list.add(imageUri)
                }

            } else { // 단일 선택
                data?.data?.let { uri ->
                    val imageUri: Uri? = data?.data
                    if (imageUri != null) {
                        list.add(imageUri)
                    }
                }
            }
            adapter.notifyDataSetChanged()

        } else {
            Toast.makeText(this, "잘못된 접근입니다", Toast.LENGTH_SHORT).show()
            return
        }

    }
}