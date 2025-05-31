package psti.unram.weektiga

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = "Login"

        val editUsername = findViewById<EditText>(R.id.editUsername)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvToSignUp = findViewById<TextView>(R.id.tvToSignUp)

        sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val usernameInput = editUsername.text.toString().trim()
            val passwordInput = editPassword.text.toString().trim()

            if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val savedUsername = sharedPref.getString("username", null)
            val savedPassword = sharedPref.getString("password", null)
            val savedEmail = sharedPref.getString("email", null)

            if (usernameInput == savedUsername && passwordInput == savedPassword) {
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()

                val user = User(usernameInput, savedEmail ?: "")
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userData", user)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        tvToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
