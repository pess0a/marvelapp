package com.pessoadev.marvelapp.presentation.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pessoadev.marvelapp.R
import com.pessoadev.marvelapp.data.model.Character
import com.pessoadev.marvelapp.util.GlideApp
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_character.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()
    private var detailComicsAdapter = DetailAdapter()
    private val detailSeriesAdapter = DetailAdapter()
    lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        character = intent?.extras?.getParcelable("character")!!
        supportActionBar?.title = character.name

        loadCharacter(character)
        viewModel.loadCharacter(character)
        loadRecyclersViews()

        viewModel.getComicsSeriesByCharacterId(character.id)

        viewModel.comicsSeriesList.observe(this, Observer {
            detailComicsAdapter.insertData(it.first)
            detailSeriesAdapter.insertData(it.second)
        })
    }

    private fun loadCharacter(character: Character) {
        textViewDescription.text = character.description

        GlideApp.with(this)
            .load("${character.thumbnail?.path}.${character.thumbnail?.extension}")
            .into(imageViewHero.imageViewHero)

    }

    private fun loadRecyclersViews() {
        val layoutManagerSerie = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
        val layoutManagerComic = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        recyclerViewSeries.apply {
            adapter = detailComicsAdapter
            setHasFixedSize(true)
            this.layoutManager = layoutManagerSerie
        }

        recyclerViewComics.apply {
            adapter = detailSeriesAdapter
            setHasFixedSize(true)
            this.layoutManager = layoutManagerComic
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        viewModel.character.observe(this, Observer {
            if (it.isFavorite) {
                menu.getItem(0).icon = getDrawable(R.drawable.ic_heath_filled_24dp)
            } else {
                menu.getItem(0).icon = getDrawable(R.drawable.ic_heart_outline_24dp)
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_favorite -> {
                viewModel.favoriteCharacter()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
