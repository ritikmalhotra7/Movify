package com.example.movieapplication.adapters

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import com.example.movieapplication.databinding.MoviesListItemsBinding
import com.example.movieapplication.models.Result

class MovieAdapter(val ls :List<Result>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(val binding : MoviesListItemsBinding) :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MoviesListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = ls.get(position)
        holder.binding.apply {
            ivPoster.load(movie.poster_path)
            ivCardbg.load(movie.poster_path){
                transformations(BlurTransformation(holder.itemView.context,10f))
            }
            idTitle.text = movie.title
            root.setOnClickListener {
                mlistener?.let {
                    it(movie)
                }
            }
        }

    }
    var mlistener : ((Result) -> Unit)? = null

    fun setOnItemCLickListener(listener : (Result)->Unit){
        mlistener = listener
    }



    override fun getItemCount(): Int {
        return ls.size
    }
}