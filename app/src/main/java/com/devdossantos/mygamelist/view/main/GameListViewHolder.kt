package com.devdossantos.mygamelist.view.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devdossantos.mygamelist.R

class GameListViewHolder (
    private val _view: View
) : RecyclerView.ViewHolder(_view) {

    private val _imageSrc by lazy { _view.findViewById<ImageView>(R.id.image_gameCard) }

    private val _title by lazy { _view.findViewById<TextView>(R.id.txt_name_gameCard) }

    private val _createdAt by lazy { _view.findViewById<TextView>(R.id.txt_createdAt_gameCard) }


    fun bind(game: MainActivity.GameReviewObjectModel) {
        _title.text = game.name
        _createdAt.text = game.createdAt.toString()
    }

}
