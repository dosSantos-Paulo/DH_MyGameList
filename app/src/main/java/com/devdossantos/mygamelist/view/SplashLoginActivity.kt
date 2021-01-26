package com.devdossantos.mygamelist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.devdossantos.mygamelist.R
import com.google.android.material.card.MaterialCardView

class SplashLoginActivity : AppCompatActivity() {

    private val _background by lazy { findViewById<ImageView>(R.id.background_splash) }

    private val _cardView by lazy { findViewById<MaterialCardView>(R.id.cardView_splashLogin) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_login)

        _background.animate().apply {
            alpha(1f)
            duration = 1500
        }

        Handler(Looper.getMainLooper()).postDelayed({
            _cardView.animate().apply {
                alpha(1f)
                duration = 1000
            }
        }, 3000)


    }
}