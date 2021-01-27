package com.devdossantos.mygamelist.view.gamedetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.devdossantos.mygamelist.R
import com.devdossantos.mygamelist.util.Constants.CREATED_AT
import com.devdossantos.mygamelist.util.Constants.DESCRIPTION
import com.devdossantos.mygamelist.util.Constants.IMAGE
import com.devdossantos.mygamelist.util.Constants.NAME
import com.devdossantos.mygamelist.view.addreview.AddActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    private val _title1 by lazy { findViewById<TextView>(R.id.txt_title_detail_1) }

    private val _title2 by lazy { findViewById<TextView>(R.id.txt_title_detail_2) }

    private val _image by lazy { findViewById<ImageView>(R.id.img_tumbnail_detail) }

    private val _createdAt by lazy { findViewById<TextView>(R.id.txt_createdAt_detail) }

    private val _description by lazy { findViewById<TextView>(R.id.txt_description_detail) }

    private lateinit var _bundleTitle: String

    private lateinit var _bundleImageUrl: String

    private lateinit var _bundleCreatedAt: String

    private lateinit var _bundleDescription: String

    private val _arrowBack by lazy { findViewById<ImageView>(R.id.arrowBack_details) }

    private val _editButton by lazy { findViewById<FloatingActionButton>(R.id.fab_edit_detail) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        _bundleTitle = intent.getStringExtra(NAME).toString()
        _bundleImageUrl = intent.getStringExtra(IMAGE).toString()
        _bundleCreatedAt = intent.getStringExtra(CREATED_AT).toString()
        _bundleDescription = intent.getStringExtra(DESCRIPTION).toString()

        _title1.text = _bundleTitle
        _title2.text = _bundleTitle
        _createdAt.text = _bundleCreatedAt
        _description.text = _bundleDescription

        _arrowBack.setOnClickListener {
            finish()
        }

        _editButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)

            intent.putExtra(NAME,_bundleTitle)
            intent.putExtra(IMAGE,_bundleImageUrl)
            intent.putExtra(DESCRIPTION, _bundleDescription)
            intent.putExtra(CREATED_AT, _bundleCreatedAt)

            startActivity(intent)
        }

    }
}