package com.devdossantos.mygamelist.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devdossantos.mygamelist.R
import com.devdossantos.mygamelist.gamereview.model.GameReviewModel
import com.devdossantos.mygamelist.view.addreview.AddActivity
import com.devdossantos.mygamelist.view.gamedetail.DetailActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val _addButton by lazy { findViewById<FloatingActionButton>(R.id.fab_add_main) }

    private val _gamesList = mutableListOf<GameReviewModel>()

    private val _recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView_games_main) }

    private val _layoutManager = GridLayoutManager(this, 2)

    private val _listAdapter = GameListAdapter(_gamesList) {
        val intent = Intent(this, DetailActivity::class.java)

        intent.putExtra("NAME",it.name)
        intent.putExtra("IMAGE",it.imageUri)
        intent.putExtra("DESCRIPTION",it.description)
        intent.putExtra("CREATED",it.createdAt.toString())

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createFakeList(10)

        _recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = _layoutManager
            adapter = _listAdapter
        }

        _addButton.setOnClickListener {

            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createFakeList(amout: Int) {

        for (i in 1..amout) {
            _gamesList.add(
                GameReviewModel(
                    "null",
                    "Teste",
                    2020,
                    "Super game, criado para ser Fake"
                )
            )
        }

    }
}