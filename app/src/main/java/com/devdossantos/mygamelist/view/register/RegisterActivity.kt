package com.devdossantos.mygamelist.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devdossantos.mygamelist.R
import com.devdossantos.mygamelist.view.main.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private val _createAcctountButton by lazy { findViewById<MaterialButton>(R.id.button_createAccount_register) }

    private val _name by lazy { findViewById<TextInputLayout>(R.id.textFile_name_register) }

    private val _email by lazy { findViewById<TextInputLayout>(R.id.textField_email_register) }

    private val _password by lazy { findViewById<TextInputLayout>(R.id.textField_password_register) }

    private val _repeatePassword by lazy { findViewById<TextInputLayout>(R.id.textField_repeatPassword_register) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        _createAcctountButton.setOnClickListener {

            if (_name.editText?.text.isNullOrEmpty()) {
                _name.editText?.error = getString(R.string.name_error)
            }

            if (_email.editText?.text.isNullOrEmpty()) {
                _email.editText?.error = getString(R.string.email_error)
            }

            if (_password.editText?.text.isNullOrEmpty()) {
                _password.editText?.error = getString(R.string.password_error)
            }

            if (_repeatePassword.editText?.text?.trim().toString() != _password.editText?.text?.trim().toString()) {
                _repeatePassword.editText?.error = getString(R.string.repeat_password_error)
            }

            if (!_name.editText?.text.isNullOrEmpty() &&
                !_email.editText?.text.isNullOrEmpty() &&
                !_password.editText?.text.isNullOrEmpty() &&
                _repeatePassword.editText?.text?.trim().toString() == _password.editText?.text?.trim().toString()
            ) {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

    }
}