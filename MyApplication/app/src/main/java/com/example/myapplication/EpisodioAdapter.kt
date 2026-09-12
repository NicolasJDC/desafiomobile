package com.example.myapplication

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class EpisodioAdapter(
    private val lista: MutableList<Episodio>
) : RecyclerView.Adapter<EpisodioAdapter.EpisodioViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null
    private var episodioTocando = -1

    class EpisodioViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtAutor: TextView = itemView.findViewById(R.id.txtAutor)
        val btnPlay: Button = itemView.findViewById(R.id.btnPlay)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnExcluir: Button = itemView.findViewById(R.id.btnExcluir)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodioViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_episodio, parent, false)

        return EpisodioViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: EpisodioViewHolder,
        position: Int
    ) {

        val episodio = lista[position]

        holder.txtTitulo.text = episodio.titulo
        holder.txtAutor.text = episodio.autor

        if (episodioTocando == position && mediaPlayer?.isPlaying == true) {
            holder.btnPlay.text = "Pause"
        } else {
            holder.btnPlay.text = "Play"
        }

        holder.btnPlay.setOnClickListener {

            val posicao = holder.adapterPosition

            if (posicao == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }

            // Se clicar no episódio que já está tocando
            if (episodioTocando == posicao && mediaPlayer != null) {

                if (mediaPlayer!!.isPlaying) {

                    mediaPlayer!!.pause()
                    holder.btnPlay.text = "Play"

                } else {

                    mediaPlayer!!.start()
                    holder.btnPlay.text = "Pause"
                }

            } else {


                mediaPlayer?.stop()
                mediaPlayer?.release()

                mediaPlayer = MediaPlayer()
                mediaPlayer!!.setVolume(1.0f, 1.0f)
                episodioTocando = posicao

                try {

                    mediaPlayer!!.setDataSource(lista[posicao].url)

                    mediaPlayer!!.setOnPreparedListener { player ->

                        player.start()

                        holder.btnPlay.text = "Pause"

                        notifyDataSetChanged()
                    }

                    mediaPlayer!!.setOnCompletionListener {

                        holder.btnPlay.text = "Play"

                        mediaPlayer?.release()
                        mediaPlayer = null

                        episodioTocando = -1

                        notifyDataSetChanged()
                    }

                    mediaPlayer!!.prepareAsync()

                    Toast.makeText(
                        holder.itemView.context,
                        "Carregando áudio...",
                        Toast.LENGTH_SHORT
                    ).show()

                } catch (e: Exception) {

                    Toast.makeText(
                        holder.itemView.context,
                        "Erro ao reproduzir áudio",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        holder.btnExcluir.setOnClickListener {

            val posicao = holder.adapterPosition

            if (posicao != RecyclerView.NO_POSITION) {


                if (episodioTocando == posicao) {

                    mediaPlayer?.stop()
                    mediaPlayer?.release()

                    mediaPlayer = null
                    episodioTocando = -1
                }

                lista.removeAt(posicao)

                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}