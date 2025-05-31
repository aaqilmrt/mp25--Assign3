package psti.unram.weektiga

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.title = "Sign Up"

        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editUsername = findViewById<EditText>(R.id.editUsername)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvToLogin = findViewById<TextView>(R.id.tvToLogin)

        sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)

        btnSignUp.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val username = editUsername.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editor = sharedPref.edit()
            editor.putString("email", email)
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()

            val user = User(username, email)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userData", user)
            startActivity(intent)
            finish()
        }

        tvToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
