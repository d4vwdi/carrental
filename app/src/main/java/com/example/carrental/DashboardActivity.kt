package com.example.carrental

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var checkBoxSedan: CheckBox
    private lateinit var checkBoxCoupe: CheckBox
    private lateinit var checkBoxSUV: CheckBox
    private lateinit var btnContinuar: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboardactivity)

        checkBoxSedan = findViewById(R.id.checkBoxSedan)
        checkBoxCoupe = findViewById(R.id.checkBoxCoupe)
        checkBoxSUV = findViewById(R.id.checkBoxSUV)
        btnContinuar = findViewById(R.id.btnContinuar)

        // Inicializa o SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Configura o listener para as checkboxes (opcional)
        checkBoxSedan.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("Você escolheu Sedan")
            }
        }

        checkBoxCoupe.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("Você escolheu Coupe")
            }
        }

        checkBoxSUV.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("Você escolheu SUV")
            }
        }

        // Configura o listener para o botão Continuar
        btnContinuar.setOnClickListener {
            // Salva a opção selecionada em SharedPreferences
            val editor = sharedPreferences.edit()
            when {
                checkBoxSedan.isChecked -> editor.putString("car_type", "Sedan")
                checkBoxCoupe.isChecked -> editor.putString("car_type", "Coupe")
                checkBoxSUV.isChecked -> editor.putString("car_type", "SUV")
            }
            editor.apply()

            // Abre a próxima activity para preenchimento dos dados do requisitante
            //startActivity(Intent(this, FormActivity::class.java))
        }
    }




    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}