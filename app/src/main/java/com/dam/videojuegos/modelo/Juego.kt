package com.dam.videojuegos.modelo

import com.google.firebase.Timestamp

data class Juego(
    var clasificacion: String = "",
    var desarrollador: String = "",
    var editor: String = "",
    var fecha: Timestamp = Timestamp.now(),
    var genero: List<String> = listOf(),
    var idioma: List<String> = listOf(),
    var precio: Double = 0.0,
    var puntuacion: Int = 0,
    var titulo: String = ""
) {
    var idJuego: String = ""

    data class Genero(val genero: String = "")
    data class Idioma(var idioma: String = "")

    companion object {
        fun listaGeneroAListaString(listaGenero: List<Genero>): List<String> {
            return listaGenero.map { it.genero }
        }

        fun listaStringAListaGenero(listaStringGenero: List<String>): List<Genero> {
            return listaStringGenero.map { Genero(it) }
        }

        fun listaIdiomaAListaString(listaIdioma: List<Idioma>): List<String> {
            return listaIdioma.map { it.idioma }
        }

        fun listaStringAListaIdioma(listaStringIdioma: List<String>): List<Idioma> {
            return listaStringIdioma.map { Idioma(it) }
        }
    }
}
