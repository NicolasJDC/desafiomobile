package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editUsuario = findViewById<EditText>(R.id.editUsuario)
        val editSenha = findViewById<EditText>(R.id.editSenha)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        val usuarioCorreto = "admin"
        val senhaCorreta = "1234"

        btnEntrar.setOnClickListener {

            val usuario = editUsuario.text.toString()
            val senha = editSenha.text.toString()

            if (usuario == usuarioCorreto && senha == senhaCorreta) {

                val intent = Intent(this, CadastroActivity::class.java)
                startActivity(intent)

            } else {

                Toast.makeText(
                    this,
                    "Usuário ou senha incorretos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

