package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multiimageactivity.Writing
import com.example.todaydrawings.R
import com.example.todaydrawings.databinding.ActivityMainBinding
import java.util.ArrayList;

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    /*액티비티 생성*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.extendedFab.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//            intent.action = Intent.ACTION_GET_CONTENT
//
//            startActivityForResult(intent, 200)

            startActivity(Intent(this, Writing::class.java))

        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView


    }

    /*Activity의 onPrepareOptionsMenu() 함수는 추가 한 옵션 메뉴(Option Menu)를 클릭할 때마다 호출됨*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        /*
        Inflate the menu; this adds items to the ac
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
        setOf(
        R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
        ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)tion bar if it is present.
        */
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /*사용자가 수행 표시줄에서 응용프로그램의 활동 계층 내에서 위로 탐색하도록 선택할 때마다 호출*/
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}






