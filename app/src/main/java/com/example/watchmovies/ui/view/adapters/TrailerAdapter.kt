package com.example.watchmovies.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmovies.R
import com.example.watchmovies.domain.model.TrailerItem
import com.example.watchmovies.ui.view.holders.TrailerHolder

class TrailerAdapter(private val trailerLst:List<TrailerItem>,
                      private val onClickListener:(TrailerItem) -> Unit): RecyclerView.Adapter<TrailerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TrailerHolder(layoutInflater.inflate(R.layout.item_trailer, parent, false))
    }

    override fun onBindViewHolder(holder: TrailerHolder, position: Int) {
        holder.render(trailerLst[position], onClickListener)
    }

    override fun getItemCount(): Int = trailerLst.size
}