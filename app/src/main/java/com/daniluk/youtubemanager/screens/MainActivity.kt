package com.daniluk.youtubemanager.screens

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.daniluk.youtubemanager.R
import com.daniluk.youtubemanager.YoutubeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<YoutubeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val navController = Navigation.findNavController(this, R.id.fragment)

        viewModel.typeFragment.observe(this, {
            if (it){
                navController.navigate(R.id.youtubeFragment)
                if (Build.VERSION.SDK_INT > 22) {
                    btPlaylist.setBackgroundColor(this.getColor(android.R.color.darker_gray))
                    btYoutube.setBackgroundColor(this.getColor(android.R.color.holo_green_light))
                }else{
                    @Suppress("DEPRECATION")
                    btPlaylist.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                    @Suppress("DEPRECATION")
                    btYoutube.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
                }
            }else{
                navController.navigate(R.id.playlistFragment)
                if (Build.VERSION.SDK_INT > 22) {
                btPlaylist.setBackgroundColor(this.getColor(android.R.color.holo_green_light))
                btYoutube.setBackgroundColor(this.getColor(android.R.color.darker_gray))
                }else {
                    @Suppress("DEPRECATION")
                    btPlaylist.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
                    @Suppress("DEPRECATION")
                    btYoutube.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                }
            }
        })

        btPlaylist.setOnClickListener {
            viewModel.typeFragment.value = false
        }

        btYoutube.setOnClickListener {
            viewModel.typeFragment.value = true
        }

    }

    //переопределение функции нажатия кнопки back
    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            //setTitle("")
            setMessage("Вы уверены, что хотите выйти из приложения?")

            setPositiveButton("Да") { _, _ ->
                super.onBackPressed()
                finish() //закрыть приложение
            }

            setNegativeButton("Нет"){_, _ ->
                // ничего не происходит, возврат в активити
            }
            setCancelable(true)
        }.create().show()
    }


}