package com.devdossantos.mygamelist.view.addreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.devdossantos.mygamelist.R
import com.google.android.material.textfield.TextInputLayout

class AddActivity : AppCompatActivity() {

    private val _title by lazy { findViewById<TextInputLayout>(R.id.textFile_name_add) }

    private val _image by lazy { findViewById<ImageView>(R.id.image_addPicutre_add) }

    private val _createdAt by lazy { findViewById<TextInputLayout>(R.id.textField_createdAt_add) }

    private val _description by lazy { findViewById<TextInputLayout>(R.id.textField_description_add) }

    private lateinit var _bundleTitle: String

    private lateinit var _bundleImageUrl: String

    private lateinit var _bundleCreatedAt: String

    private lateinit var _bundleDescription: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        _bundleTitle = intent.getStringExtra("NAME").toString()
        _bundleImageUrl = intent.getStringExtra("IMAGE").toString()
        _bundleCreatedAt = intent.getStringExtra("CREATED").toString()
        _bundleDescription = intent.getStringExtra("DESCRIPTION").toString()

    }
}