package com.devdossantos.mygamelist.view.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devdossantos.mygamelist.R
import com.devdossantos.mygamelist.util.Constants.CREATED_AT
import com.devdossantos.mygamelist.util.Constants.DESCRIPTION
import com.devdossantos.mygamelist.util.Constants.IMAGE
import com.devdossantos.mygamelist.util.Constants.NAME
import com.devdossantos.mygamelist.view.addreview.AddActivity
import com.devdossantos.mygamelist.view.gamedetail.DetailActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class MainActivity : AppCompatActivity() {

    data class Post (
        var list: MutableList<String> = mutableListOf()
    )

    data class GameReviewModel(
        var createdAt: String = "",
        var description: String = "",
        var id: String = "",
        var name: String = ""
    )

    data class GameReviewObjectModel (
        var imageUri: String = "",
        var name: String = "",
        var createdAt: String = "",
        var description: String = ""
    )

    private val _addButton by lazy { findViewById<FloatingActionButton>(R.id.fab_add_main) }

    private val _gamesList = mutableListOf<GameReviewObjectModel>()

    private val _recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView_games_main) }

    private val _layoutManager = GridLayoutManager(this, 2)

    private val database = FirebaseDatabase.getInstance()

    private val myRef = database.getReference("game-review")

    private val gameIdsRef = database.getReference("game-id")

    private val myStorage = FirebaseStorage.getInstance().getReference("game-review-picture")

    private var user = FirebaseAuth.getInstance().currentUser!!

    private val newPost = Post()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getIds()

        Handler(Looper.getMainLooper()).postDelayed({
            val _listAdapter = GameListAdapter(_gamesList) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)

                intent.putExtra(NAME, it.name)
                intent.putExtra(IMAGE, it.imageUri)
                intent.putExtra(DESCRIPTION, it.description)
                intent.putExtra(CREATED_AT, it.createdAt)

                startActivity(intent)
            }

            _recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = _layoutManager
                adapter = _listAdapter
            }

        }, 5000)


        _addButton.setOnClickListener {

            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getIds(){

        gameIdsRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(AddActivity.Post::class.java)

                value?.list?.forEach {
                    myRef.child(it).addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val value = dataSnapshot.getValue(GameReviewModel::class.java)!!
                            myStorage.child(value.id).downloadUrl.addOnSuccessListener {
                                _gamesList.add(
                                    GameReviewObjectModel(
                                        it.toString(),
                                        value.name,
                                        value.createdAt,
                                        value.description
                                    )
                                )
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {}
                    })
                }
            }

        })
    }

    private fun createFakeList(amount: Int) {

        for (i in 1..amount) {
            _gamesList.add(
                GameReviewObjectModel(
                    "null",
                    "Exemplo",
                    "2020",
                    "Super game, criado para ser Fake"
                )
            )
        }

    }
}
