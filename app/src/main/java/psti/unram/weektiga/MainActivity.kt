package psti.unram.weektiga

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Halaman Utama"

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)

        // Ambil data dari Parcelable
        val user = intent.getParcelableExtra<User>("userData")
        tvWelcome.text = "Selamat datang, ${user?.name ?: "Pengguna"}!"

        btnLogout.setOnClickListener {
            val editor = sharedPref.edit()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
