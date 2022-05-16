package com.example.movieapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.complete.newsreporter.utils.Resources
import com.example.movieapplication.R
import com.example.movieapplication.adapters.MovieAdapter
import com.example.movieapplication.database.MovieDatabase
import com.example.movieapplication.database.MovieRepository
import com.example.movieapplication.models.Result
import com.example.movieapplication.utils.Constants.Companion.TAG
import com.example.movieapplication.viewmodels.MovieViewModel
import com.example.movieapplication.viewmodels.MovieViewModelFactory
import kotlinx.android.synthetic.main.fragment_popular_movie.*

class PopularMovieFragment : Fragment(R.layout.fragment_popular_movie) {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel()


    }
    private fun viewModel(){
        val repository  = MovieRepository(MovieDatabase(requireActivity()))
        val viewModelFactory = MovieViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MovieViewModel::class.java)

        viewModel.getPopular()
        viewModel.popularMovieLs.observe(viewLifecycleOwner, Observer{response->
            when(response){
                is Resources.Success ->{
                    response.data?.let {movieResponse ->
                        Log.d(TAG,movieResponse.toString())
                        setupRV(movieResponse.results!!)
                    }
                }
                is Resources.Error ->{
                    response.data?.let{
                        Toast.makeText(requireContext(),"An Error occured $it", Toast.LENGTH_SHORT).show()
                        Log.d(TAG,"Something is wrong")
                    }
                }
                is Resources.Loading ->{
                }
            }
        })
    }
    private fun setupRV(ls:List<Result>){
        movieAdapter = MovieAdapter(ls)
        rv_popular.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = movieAdapter
        }
        movieAdapter.setOnItemCLickListener{
            val bundle = Bundle().apply {
                putSerializable("movie",it)
            }
            findNavController().navigate(R.id.action_popularMovieFragment_to_movieFragment,bundle)
        }
    }
}