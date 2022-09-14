package com.pa.pakotlin.presentation.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pa.pakotlin.R
import com.pa.pakotlin.adapter.AdapterPokemon
import com.pa.pakotlin.databinding.FrgPokemonBinding
import com.pa.pakotlin.presentation.viewmodel.PokemonViewModel

class FrgPokemon : Fragment() {
    private lateinit var binding: FrgPokemonBinding
    private val viewModelPokemon: PokemonViewModel by viewModels()
    private var adapter: AdapterPokemon? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrgPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModelPokemon.getPokemonData()
        binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        viewModelPokemon.pokemon.observe(viewLifecycleOwner) {
            binding.viewLoading.isVisible = false
            binding.rvPokemon.isVisible = true
            adapter = AdapterPokemon(it.listPokemon)
            binding.rvPokemon.adapter = adapter
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_recycler, menu)
        val searchView = menu.findItem(R.id.actionSearch).actionView as SearchView
        searchView.queryHint = "Buscar ...."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                println("Query $query")
                adapter?.filter?.filter(query)
                return true
            }
        })
    }
}
