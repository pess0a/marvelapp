package com.pessoadev.marvelapp.presentation.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pessoadev.marvelapp.R
import com.pessoadev.marvelapp.data.model.ComicSerie
import com.pessoadev.marvelapp.util.AppUtil
import kotlinx.android.synthetic.main.item_series_comics.view.*


class DetailAdapter :
    RecyclerView.Adapter<DetailAdapter.SeriesComicsViewHolder>() {
    private val comicSerieList: MutableList<ComicSerie> = mutableListOf()
    lateinit var context: Context

    fun insertData(comicSerieList: MutableList<ComicSerie>) {
        this.comicSerieList.addAll(comicSerieList)
        notifyDataSetChanged()
    }

    inner class SeriesComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(comicSerie: ComicSerie) {
            itemView.textViewName.text = comicSerie.name
            AppUtil.loadImageWithHeader(
                context,
                comicSerie.imageUrl,
                itemView.imageViewHero
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesComicsViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_series_comics, parent, false)
        return SeriesComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesComicsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return comicSerieList.size
    }

    private fun getItem(position: Int): ComicSerie {
        return comicSerieList[position]
    }


}