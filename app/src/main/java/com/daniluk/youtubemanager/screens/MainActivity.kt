package com.daniluk.youtubemanager.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.daniluk.youtubemanager.R
import com.daniluk.youtubemanager.YoutubeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<YoutubeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        btPlaylist.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
        btYoutube.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))

        val navController = Navigation.findNavController(this, R.id.fragment)

        viewModel.typeFragment.observe(this, {
            if (it){
                navController.navigate(R.id.youtubeFragment)
                btPlaylist.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                btYoutube.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
            }else{
                navController.navigate(R.id.playlistFragment)
                btPlaylist.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
                btYoutube.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            }
        })

        btPlaylist.setOnClickListener {
            viewModel.typeFragment.value = false
        }

        btYoutube.setOnClickListener {
            viewModel.typeFragment.value = true
        }

    }

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