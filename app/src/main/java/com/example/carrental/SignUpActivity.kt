package com.example.carrental

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var edtUsuario: EditText
    private lateinit var edtSenha: EditText
    private lateinit var edtConfirmarSenha: EditText
    private lateinit var btnSave: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupactivity)

        // Inicializa os componentes do layout
        edtUsuario = findViewById(R.id.edtSignupUser)
        edtSenha = findViewById(R.id.edtSignupSenha)
        edtConfirmarSenha = findViewById(R.id.edtSignupConfirmarSenha)
        btnSave = findViewById(R.id.btnCadastrar)
        tvError = findViewById(R.id.tvError)

        // Inicializa o SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        btnSave.setOnClickListener {
            val loginUser = edtUsuario.text.toString().trim()
            val loginSenha = edtSenha.text.toString().trim()
            val confirmPassword = edtConfirmarSenha.text.toString().trim()

            // Salva os dados utilizando SharedPreferences
            if (loginSenha == confirmPassword) {
                // Senhas coincidem, salvar os dados utilizando SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("username", loginUser) // Usando a chave correta "username"
                editor.putString("password", loginSenha) // Usando a chave correta "password"
                editor.apply()

                // Exibe mensagem de sucesso e retorna para a LoginActivity
                Toast.makeText(this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish() // Termina a SignUpActivity para evitar que o usuário volte pressionando o botão "voltar"
            } else {
                // Senhas não coincidem, mostrar mensagem de erro
                tvError.visibility = TextView.VISIBLE
                tvError.text = "As senhas não coincidem"
                Toast.makeText(this, "As senhas precisam coincidir", Toast.LENGTH_SHORT).show()
            }
        }
    }
}