package com.daniluk.youtubemanager.screens

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniluk.youtubemanager.adapters.AdapterSearchVideo
import com.daniluk.youtubemanager.R
import com.daniluk.youtubemanager.YoutubeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_youtube.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val viewModel by activityViewModels<YoutubeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val adapter = AdapterSearchVideo()
        rvSearchVideo.layoutManager = LinearLayoutManager(context)
        rvSearchVideo.adapter = adapter

        viewModel.searchListVideo.observe(this,{
            if (it.isEmpty()){
                btAdd.visibility = View.INVISIBLE
            }else{
                btAdd.visibility = View.VISIBLE
            }
            adapter.listVideo = it
        })

        btAdd.setOnClickListener {
            val list = viewModel.searchListVideo.value ?: return@setOnClickListener
            for (video in list){
                if (video.checkBox){
                    val set = viewModel.playListVideo.value?.toMutableSet()
                    set?.add(video)
                    viewModel.playListVideo.value = set?.toList()?.toMutableList()
                }
            }
        }

        //etEnterStringSearch.text = "Meral"
        btSearch.setOnClickListener {
            val str = etEnterStringSearch.text.toString().trim()
            if (! str.isEmpty()) {
                viewModel.searchVideo(str)
            }else return@setOnClickListener
        }




    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}