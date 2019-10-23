package com.pessoadev.marvelapp.di.module

import com.pessoadev.marvelapp.presentation.characters.fragments.CharactersAdapter
import com.pessoadev.marvelapp.presentation.characters.fragments.CharactersViewModel
import com.pessoadev.marvelapp.presentation.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { CharactersViewModel(get()) }
    viewModel { DetailViewModel(get()) }

    factory { CharactersAdapter() }

    single { createMarvelRepository(get(), get()) }
}