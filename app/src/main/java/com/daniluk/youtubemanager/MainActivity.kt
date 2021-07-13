package com.daniluk.youtubemanager

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

        val navController = Navigation.findNavController(this, R.id.fragment)

        btPlaylist.setOnClickListener {
            navController.navigate(R.id.playlistFragment)
        }

        btYoutube.setOnClickListener {
            navController.navigate(R.id.youtubeFragment)
        }

        viewModel.searchListVideo.add(YoutubeVideo("Title_1", "Description_1"))
        viewModel.searchListVideo.add(YoutubeVideo("Title_2", "Description_3"))
        viewModel.searchListVideo.add(YoutubeVideo("Title_3", "Description_3"))
    }
}