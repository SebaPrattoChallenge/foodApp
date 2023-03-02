package com.sebapp.challenge.presentation.foodList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebapp.challenge.R
import com.sebapp.challenge.data.model.Food
import com.sebapp.challenge.databinding.ItemRowBinding

class RecyclerViewAdapter() : ListAdapter<Food, RecyclerViewAdapter.MyViewHolder>(
    DiffUtilCallBack()
) {
    private lateinit var context: Context
    private var onItemClickListener: ((Food) -> Unit)? = null

    fun setOnItemClickListener(listener: (Food) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val inflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return MyViewHolder(inflater)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRowBinding.bind(view)
        private var currentItem: Food? = null

        init {
            binding.itemList.setOnClickListener {
                currentItem?.let { item ->
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }

        fun bind(data: Food) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            currentItem = data
            binding.textViewName.text = data.name
            Glide.with(binding.imageView)
                .load(data.image)
                .centerCrop()
                .into(binding.imageView)
            itemView.startAnimation(animation)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.image == newItem.image
        }
    }
}
