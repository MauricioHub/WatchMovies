package com.example.watchmovies.ui.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmovies.databinding.ItemTrailerBinding
import com.example.watchmovies.domain.model.TrailerItem

class TrailerHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemTrailerBinding.bind(view)

    fun render(trailerItem: TrailerItem, onClickListener:(TrailerItem) -> Unit){
        binding.tvTrailerName.text = trailerItem.name
        itemView.setOnClickListener { onClickListener(trailerItem) }
    }
}