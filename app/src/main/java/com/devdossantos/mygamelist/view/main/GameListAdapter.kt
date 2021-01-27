package com.devdossantos.mygamelist.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devdossantos.mygamelist.R
import com.devdossantos.mygamelist.gamereview.model.GameReviewModel

class GameListAdapter(
    private val _gameList: List<GameReviewModel>,
    private val _listener: (GameReviewModel) -> Unit
) : RecyclerView.Adapter<GameListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.game_card,
            parent,
            false
        )

        return GameListViewHolder(view)
    }

    override fun getItemCount() = _gameList.size

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        val item = _gameList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { _listener(item) }
    }
}