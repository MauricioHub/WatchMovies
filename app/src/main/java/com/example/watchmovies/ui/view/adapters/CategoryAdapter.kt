package com.example.watchmovies.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmovies.R
import com.example.watchmovies.data.model.CategoryModel
import com.example.watchmovies.ui.view.holders.CategoryHolder

class CategoryAdapter(private val categoriesLst:List<CategoryModel>,
                      private val onClickListener:(CategoryModel) -> Unit): RecyclerView.Adapter<CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryHolder(layoutInflater.inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.render(categoriesLst[position], onClickListener)
    }

    override fun getItemCount(): Int = categoriesLst.size
}