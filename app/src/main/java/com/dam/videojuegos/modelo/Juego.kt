package com.dam.videojuegos.modelo

import com.google.firebase.Timestamp
import java.util.Date

data class Juego(
    var clasificacion: String = "",
    var desarrollador: Any = mutableListOf<String>(),
    var descripcion: String = "",
    var editor: Any = mutableListOf<String>(),
    var fecha: Date = Date(),
    var genero: Any = mutableListOf<String>(),
    var idioma: Any = mutableListOf<String>(),
    var precio: Double = 0.0,
    var puntuacion: Int = 0,
    var titulo: String = "",
    var foto: String = ""
) {
    var idJuego: String = ""
}

fun Date.convertirATimestamp(): Timestamp {
    return Timestamp(this)
}

fun Timestamp.convertirADate(): Date {
    return Date(this.toDate().time)
}
