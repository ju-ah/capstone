package com.example

import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView

class GalleryCheck : AppCompatActivity() {
    @RequiresApi(api = Build.VERSION_CODES.M)
    //권한 체크 하기
    fun checkPermission(activity: AppCompatActivity): Boolean {
        val isPermission =
            ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        return if (isPermission == PackageManager.PERMISSION_DENIED) {
            val b = AlertDialog.Builder(activity)
            b.setMessage("이미지를 등록하기위해선 저장소 읽기 권한이 필요합니다. 허용하시겠습니까?")

            b.setPositiveButton("yes") { dialogInterface: DialogInterface?, i: Int ->
                activity.requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    0x01
                )
            }
            b.setNegativeButton("no") { dialogInterface: DialogInterface?, i: Int -> activity.finish() }
            b.show()
            false
        } else {
            true
        }
    }

    //커서를 화면으로 보여주는 라이브러리 인터페이스
    interface CursorVisualizer {
        fun attachRecyclerView(recyclerView: RecyclerView)
        fun setCursor(cursor: Cursor)
        fun showDialog(context: Context, cursor: Cursor)


    }
}



