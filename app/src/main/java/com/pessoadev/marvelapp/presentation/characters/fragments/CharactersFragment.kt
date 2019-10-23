package com.pessoadev.marvelapp.presentation.characters.fragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pessoadev.marvelapp.R
import com.pessoadev.marvelapp.data.model.Character
import com.pessoadev.marvelapp.presentation.detail.DetailActivity
import com.pessoadev.marvelapp.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.characters_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel


class CharactersFragment : Fragment() {

    companion object {
        fun newInstance() =
            CharactersFragment()
    }

    private val viewModel: CharactersViewModel by sharedViewModel()
    private val charactersAdapter: CharactersAdapter by inject()
    lateinit var layoutManagerGrid: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.characters_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupCharacterRecyclerView()
        viewModel.getCharacters()

        viewModel.listCharacters.observe(this, Observer {
            if (it != null) updateCharacterList(it)
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.INVISIBLE
            }
        })

        viewModel.errorConnection.observe(this, Observer { hasError ->
            if (hasError) {
                charactersRecyclerView.visibility = View.INVISIBLE
                layoutInternetError.visibility = View.VISIBLE
            } else {
                charactersRecyclerView.visibility = View.VISIBLE
                layoutInternetError.visibility = View.INVISIBLE
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            textViewErrorMessage.text = it
        })

        charactersAdapter.setOnFavoriteClickListener {
            viewModel.saveCharacter(it)
        }

        charactersAdapter.setOnUnFavoriteClickListener {
            viewModel.deleteCharacter(it)
        }

        charactersAdapter.setOnClickCharacterListener {
            startActivity(Intent(activity, DetailActivity::class.java).apply {
                putExtra("character", it)
            })
        }

        textViewTryAgain.setOnClickListener {
            viewModel.getCharacters()
        }


    }

    private fun setupCharacterRecyclerView() {
        layoutManagerGrid =
            if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(activity, 2)
            } else {
                GridLayoutManager(activity, 3)
            }

        charactersRecyclerView.apply {
            adapter = charactersAdapter
            setHasFixedSize(true)
            this.layoutManager = layoutManagerGrid
        }

        charactersRecyclerView.addOnScrollListener(
            object : EndlessRecyclerViewScrollListener(layoutManagerGrid) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    viewModel.getCharacters()
                }
            }
        )

    }

    private fun updateCharacterList(charactersList: List<Character>) {
        charactersAdapter.addCharacters(charactersList)
    }

    override fun onResume() {
        viewModel.verifyLocalFavorites()
        super.onResume()


    }

}
