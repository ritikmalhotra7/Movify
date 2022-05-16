package com.example.movieapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.BlurTransformation
import coil.transform.GrayscaleTransformation
import com.example.movieapplication.R
import com.example.movieapplication.utils.Constants.Companion.URLIMAGE
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {

    val args : MovieFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews(args)


    }
    private fun setViews(args: MovieFragmentArgs){
        val movie = args.movie
        iv_movie_poster.load(URLIMAGE+movie.poster_path){
            placeholder(R.drawable.icons8_movie)
        }
        iv_bg_movie.load(URLIMAGE+movie.poster_path){
            crossfade(1000)
            transformations(
                GrayscaleTransformation(),
                BlurTransformation(requireContext(),10f)

            )
        }
        tv_movie_title.text = movie.title
        tv_release_date.text = movie.release_date
        tv_movie_desc.text = movie.overview
        tv_movie_vote.text = movie.vote_average.toString()

    }
}