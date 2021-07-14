package com.daniluk.youtubemanager

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
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

        btPlaylist.setOnClickListener {
            navController.navigate(R.id.playlistFragment)
            btPlaylist.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
            btYoutube.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
        }

        btYoutube.setOnClickListener {
            navController.navigate(R.id.youtubeFragment)
            btPlaylist.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            btYoutube.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        }

    }
}