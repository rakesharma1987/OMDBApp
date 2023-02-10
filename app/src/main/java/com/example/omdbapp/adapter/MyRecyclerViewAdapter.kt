package com.example.omdbapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.omdbapp.databinding.ListItemBinding
import com.example.omdbapp.repository.model.Movie
import com.squareup.picasso.Picasso

class MyRecyclerViewAdapter(private val movieList: List<Movie>, private val context: Context): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie: Movie = movieList[position]
        holder.bind(movie)
    }

    inner class MyViewHolder(private val listItemBinding: ListItemBinding): RecyclerView.ViewHolder(listItemBinding.root){
        fun bind(movie: Movie){
            listItemBinding.titleTextView.text = movie.title
            listItemBinding.descriptionTextView.text = movie.type+" | "+movie.year+" | "+movie.imdbID
            Picasso.with(context).load(movie.poster).into(listItemBinding.imageView)
        }
    }
}