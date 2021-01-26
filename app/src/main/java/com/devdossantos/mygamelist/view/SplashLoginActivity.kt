package com.devdossantos.mygamelist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.devdossantos.mygamelist.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputLayout

class SplashLoginActivity : AppCompatActivity() {

    private val _background by lazy { findViewById<ImageView>(R.id.background_splash) }

    private val _cardView by lazy { findViewById<MaterialCardView>(R.id.cardView_splashLogin) }

    private val _createAcountButton by lazy { findViewById<MaterialButton>(R.id.button_createAccount_splashLogin) }

    private val _loginButton by lazy { findViewById<MaterialButton>(R.id.button_login_splashLogin) }

    private val _email by lazy { findViewById<TextInputLayout>(R.id.textFile_email_splashLogin) }

    private val _password by lazy { findViewById<TextInputLayout>(R.id.textField_password_splashLogin) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_login)

        splashScreenAnimation()

        _loginButton.setOnClickListener {

            if (_email.editText?.text.isNullOrEmpty()) {
                _email.editText?.error = getString(R.string.email_error)
            }

            if (_password.editText?.text.isNullOrEmpty()) {
                _password.editText?.error = getString(R.string.password_error)
            }

            if (!_email.editText?.text.isNullOrEmpty() && !_password.editText?.text.isNullOrEmpty()) {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

        _createAcountButton.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    private fun splashScreenAnimation() {
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