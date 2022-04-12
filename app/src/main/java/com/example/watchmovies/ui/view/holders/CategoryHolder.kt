package com.example.watchmovies.ui.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmovies.data.model.CategoryModel
import com.example.watchmovies.databinding.ItemCategoryBinding

class CategoryHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemCategoryBinding.bind(view)

    fun render(categoryModel: CategoryModel, onClickListener:(CategoryModel) -> Unit){
        binding.tvCategoryName.text = categoryModel.name
        itemView.setOnClickListener { onClickListener(categoryModel) }
    }
}