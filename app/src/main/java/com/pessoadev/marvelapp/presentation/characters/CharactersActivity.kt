package com.pessoadev.marvelapp.presentation.characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pessoadev.marvelapp.R
import com.pessoadev.marvelapp.presentation.characters.fragments.CharactersFragment
import com.pessoadev.marvelapp.presentation.characters.fragments.FavoriteFragment
import kotlinx.android.synthetic.main.activity_character.*


class CharactersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        toolbar.title = getString(R.string.app_name)
        setupViewPager()
    }

    private fun setupViewPager() {

        val charactersListFragment = CharactersFragment.newInstance()
        val favoriteListFragment = FavoriteFragment.newInstance()

        CharactersPageAdapter(supportFragmentManager).apply {
            addFragment(charactersListFragment, getString(R.string.characters))
            addFragment(favoriteListFragment, getString(R.string.favorites))
        }.also { characterPageAdapter ->
            viewpager.adapter = characterPageAdapter
        }

        tabs.setupWithViewPager(viewpager)
    }
}
