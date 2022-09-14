package com.pa.pakotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.pa.pakotlin.R
import com.pa.pakotlin.databinding.ItemPokemonBinding
import com.pa.pakotlin.presentation.model.PokemonName

class AdapterPokemon(private val pokemonList: List<PokemonName>) :
    RecyclerView.Adapter<AdapterPokemon.ViewHolder>(), Filterable {
    private var pokemonFilterable: List<PokemonName> = pokemonList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemonFilterable[position])
    }

    override fun getItemCount() = pokemonFilterable.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPokemonBinding.bind(itemView)

        fun bind(pokemon: PokemonName) {
            binding.chbPokemon.setOnCheckedChangeListener { _, b ->
                pokemon.checkBox = b
            }
            binding.tvPokemonName.text = pokemon.name
            binding.chbPokemon.isChecked = pokemon.checkBox
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val inputText = query.toString()

                pokemonFilterable = if (inputText.isEmpty()) {
                    pokemonList
                } else {
                    val pokemonFilterableTemp: MutableList<PokemonName> = ArrayList()
                    pokemonFilterableTemp.clear()

                    pokemonFilterable.forEach {
                        if (it.name.contains(inputText)) pokemonFilterableTemp.add(it)
                    }
                    pokemonFilterableTemp
                }
                val filterResult = FilterResults()
                filterResult.values = pokemonFilterable
                return filterResult
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(query: CharSequence?, results: FilterResults?) {
                pokemonFilterable = results?.values as List<PokemonName>
                notifyDataSetChanged()
            }
        }
    }
}
