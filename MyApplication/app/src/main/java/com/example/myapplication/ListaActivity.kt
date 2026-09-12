package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val recyclerEpisodios =
            findViewById<RecyclerView>(R.id.recyclerEpisodios)

        recyclerEpisodios.layoutManager =
            LinearLayoutManager(this)

        recyclerEpisodios.adapter =
            EpisodioAdapter(Repositorio.episodios)
    }
}