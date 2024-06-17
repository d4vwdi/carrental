package com.example.carrental

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginactivity)

        val edtLoginUser = findViewById<EditText>(R.id.edtLoginUser)
        val edtLoginSenha = findViewById<EditText>(R.id.edtLoginSenha)
        val btnLoginSignup = findViewById<Button>(R.id.btnLoginSignup)
        val btnLoginEnter = findViewById<Button>(R.id.btnLoginEnter)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Configura o clique do botão para entrar
        btnLoginEnter.setOnClickListener {
            val loginUser = edtLoginUser.text.toString().trim()
            val loginSenha = edtLoginSenha.text.toString().trim()

            // Verifica se os campos de usuário e senha estão preenchidos
            if (loginUser.isEmpty() || loginSenha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha usuário e senha", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Recupera os dados salvos em SharedPreferences
            val savedUsername = sharedPreferences.getString("username", "")
            val savedPassword = sharedPreferences.getString("password", "")

            // Verifica se os dados de login coincidem com os salvos em SharedPreferences
            if (loginUser == savedUsername && loginSenha == savedPassword) {
                // Dados corretos, navega para o Dashboard ou próxima tela
                startActivity(Intent(this, DashboardActivity::class.java))
                finish() // Termina a LoginActivity para evitar que o usuário volte pressionando o botão "voltar"
            } else {
                // Dados incorretos, exibir mensagem de erro
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }

        btnLoginSignup.setOnClickListener {
            // Criando uma Intent para iniciar SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}