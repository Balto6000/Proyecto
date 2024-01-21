package com.dam.videojuegos.modelo

import com.google.firebase.Timestamp
import java.util.Date

data class Juego(
    var clasificacion: String = "",
    var desarrollador: List<String> = mutableListOf(),
    var editor: String = "",
    var fecha: Date = Date(),
    var genero: List<String> = mutableListOf(),
    var idioma: List<String> = mutableListOf(),
    var precio: Double = 0.0,
    var puntuacion: Int = 0,
    var titulo: String = ""
) {
    var idJuego: String = ""
}

fun Date.convertirATimestamp(): Timestamp {
    return Timestamp(this)
}

fun Timestamp.convertirADate(): Date {
    return Date(this.toDate().time)
}
