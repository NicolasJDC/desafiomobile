package com.example.myapplication
import android.content.Intent

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val editTitulo = findViewById<EditText>(R.id.editTitulo)
        val editAutor = findViewById<EditText>(R.id.editAutor)
        val editUrl = findViewById<EditText>(R.id.editUrl)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        val txtVerLista = findViewById<TextView>(R.id.txtVerLista)

        btnSalvar.setOnClickListener {

            val titulo = editTitulo.text.toString()
            val autor = editAutor.text.toString()
            val url = editUrl.text.toString()

            if (titulo.isEmpty() || autor.isEmpty() || url.isEmpty()) {

                Toast.makeText(
                    this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val episodio = Episodio(
                    titulo = titulo,
                    autor = autor,
                    url = url
                )

                Repositorio.episodios.add(episodio)

                Toast.makeText(
                    this,
                    "Episódio salvo!",
                    Toast.LENGTH_SHORT
                ).show()

                editTitulo.text.clear()
                editAutor.text.clear()
                editUrl.text.clear()
            }
        }

        txtVerLista.setOnClickListener {

            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)

        }
    }
}