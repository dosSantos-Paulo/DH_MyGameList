package com.devdossantos.mygamelist.view.addreview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.devdossantos.mygamelist.R
import com.devdossantos.mygamelist.util.Constants.CONTEXT_RESQUEST_CODE
import com.devdossantos.mygamelist.util.Constants.CREATED_AT
import com.devdossantos.mygamelist.util.Constants.DESCRIPTION
import com.devdossantos.mygamelist.util.Constants.IMAGE
import com.devdossantos.mygamelist.util.Constants.NAME
import com.devdossantos.mygamelist.view.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso

class AddActivity : AppCompatActivity() {

    private var _imageUri: Uri? = null

    private var _imageValidator: Boolean = false

    private val _title by lazy { findViewById<TextInputLayout>(R.id.textFile_name_add) }

    private val _image by lazy { findViewById<ImageView>(R.id.image_addPicutre_add) }

    private val _coverIcon by lazy { findViewById<ImageView>(R.id.image_camera_add) }

    private val _createdAt by lazy { findViewById<TextInputLayout>(R.id.textField_createdAt_add) }

    private val _description by lazy { findViewById<TextInputLayout>(R.id.textField_description_add) }

    private val _saveButton by lazy { findViewById<Button>(R.id.button_saveGame_add) }

    private var _bundleTitle: String? = null

    private var _bundleImageUrl: String? = null

    private var _bundleCreatedAt: String? = null

    private var _bundleDescription: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        _bundleTitle = intent.getStringExtra(NAME)
        _bundleImageUrl = intent.getStringExtra(IMAGE)
        _bundleCreatedAt = intent.getStringExtra(CREATED_AT)
        _bundleDescription = intent.getStringExtra(DESCRIPTION)

        nullValidation()

        _title.editText?.setText(_bundleTitle)
        _createdAt.editText?.setText(_bundleCreatedAt)
        _description.editText?.setText(_bundleDescription)

        _image.setOnClickListener {
            findFile()
        }

        _saveButton.setOnClickListener {
            if (_title.editText?.text.isNullOrEmpty()) {
                _title.error = getString(R.string.title_error)
            }
            if (_createdAt.editText?.text.isNullOrEmpty()) {
                _createdAt.error = getString(R.string.created_at_error)
            }
            if (_description.editText?.text.isNullOrEmpty()) {
                _description.error = getString(R.string.description_error)
            }
            if (!_imageValidator) {
                Snackbar.make(
                    it, "Replace a picture!!",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            if (!_title.editText?.text.isNullOrEmpty() &&
                !_createdAt.editText?.text.isNullOrEmpty() &&
                !_description.editText?.text.isNullOrEmpty() &&
                _imageValidator
            ) {
                Toast.makeText(this, "Send", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CONTEXT_RESQUEST_CODE && resultCode == RESULT_OK) {
            _imageUri = data?.data
            _coverIcon.animate().alpha(0f)
            _imageValidator = true
            Picasso.get().load(_imageUri).into(_image)
        }
    }

    private fun nullValidation() {
        if (_bundleTitle == null) {
            _bundleTitle = ""
        }
        if (_bundleCreatedAt == null) {
            _bundleTitle = ""
        }
        if (_bundleDescription == null) {
            _bundleTitle = ""
        }
        if (_bundleImageUrl != null) {
            _coverIcon.alpha = 0f
           _imageValidator = true
            Picasso.get().load(_bundleImageUrl).into(_image)
        }
    }

    private fun findFile() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, CONTEXT_RESQUEST_CODE)
    }
}