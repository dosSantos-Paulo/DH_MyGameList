package com.devdossantos.mygamelist.view.splash


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devdossantos.mygamelist.R
import com.devdossantos.mygamelist.view.main.MainActivity
import com.devdossantos.mygamelist.view.register.RegisterActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class SplashLoginActivity : AppCompatActivity() {

    private val _background by lazy { findViewById<ImageView>(R.id.background_splash) }

    private val _cardView by lazy { findViewById<MaterialCardView>(R.id.cardView_splashLogin) }

    private val _createAcountButton by lazy { findViewById<MaterialButton>(R.id.button_createAccount_splashLogin) }

    private val _loginButton by lazy { findViewById<MaterialButton>(R.id.button_login_splashLogin) }

    private val _email by lazy { findViewById<TextInputLayout>(R.id.textFile_email_splashLogin) }

    private val _password by lazy { findViewById<TextInputLayout>(R.id.textField_password_splashLogin) }

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_login)

        mAuth = FirebaseAuth.getInstance()

        splashScreenAnimation()

        _loginButton.setOnClickListener {

            if (_email.editText?.text.isNullOrEmpty()) {
                _email.editText?.error = getString(R.string.email_error)
            }

            if (_password.editText?.text.isNullOrEmpty()) {
                _password.editText?.error = getString(R.string.password_error)
            }

            if (!_email.editText?.text.isNullOrEmpty() && !_password.editText?.text.isNullOrEmpty()) {
                signInFirebase(
                    _email.editText?.text.toString(),
                    _password.editText?.text.toString()
                )
            }

        }

        _createAcountButton.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    private fun toHome() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
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

    private fun signInFirebase(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    toHome()
                    Log.d("FIREBASE", "signInWithEmail:success")
                } else {

                    Log.w(
                        "FIREBASE",
                        "signInWithEmail:failure",
                        task.exception
                    )
                    Toast.makeText(
                        this@SplashLoginActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
    }
}