package com.pessoadev.marvelapp.presentation.characters.fragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.pessoadev.marvelapp.R
import com.pessoadev.marvelapp.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.favorite_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() =
            FavoriteFragment()
    }

    private val viewModel: CharactersViewModel by sharedViewModel()
    private val charactersAdapter: CharactersAdapter by inject()
    lateinit var layoutManagerGrid: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupFavoriteRecyclerView()
        viewModel.getFavorites()

        viewModel.listCharactersFavorites.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                imageViewNoFavorite.visibility = View.VISIBLE
                textViewNoFavorite.visibility = View.VISIBLE

            } else {
                imageViewNoFavorite.visibility = View.INVISIBLE
                textViewNoFavorite.visibility = View.INVISIBLE
            }
            charactersAdapter.addCharacters(it)
        })

        charactersAdapter.setOnUnFavoriteClickListener {
            viewModel.deleteCharacter(it)
        }

        charactersAdapter.setOnClickCharacterListener {
            startActivity(Intent(activity, DetailActivity::class.java).apply {
                putExtra("character", it)
            })
        }
    }

    private fun setupFavoriteRecyclerView() {
        layoutManagerGrid =
            if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(activity, 2)
            } else {
                GridLayoutManager(activity, 3)
            }

        favoriteRecyclerView.adapter = charactersAdapter
        favoriteRecyclerView.setHasFixedSize(true)
        favoriteRecyclerView.layoutManager = layoutManagerGrid
    }


}
