package com.pa.pakotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pa.pakotlin.R
import com.pa.pakotlin.databinding.ItemContentBinding
import com.pa.pakotlin.model.SuperHero

class AdapterContent(private val superHero: List<SuperHero>) :
    RecyclerView.Adapter<AdapterContent.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(superHero[position])
    }

    override fun getItemCount() = superHero.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemContentBinding.bind(itemView)

        fun bind(superHero: SuperHero) {
            binding.tvSuperhero.text = superHero.superHero
            binding.tvRealName.text = superHero.realName
            binding.tvPublisher.text = superHero.publisher
            Glide.with(binding.ivAvatar.context).load(superHero.photo).into(binding.ivAvatar)
        }
    }
}
