package com.dam.videojuegos.shared

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.dam.videojuegos.modelo.Juego
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelFirebase : ViewModel() {

    val conexion = FirebaseFirestore.getInstance()

    private lateinit var ListenerReg: ListenerRegistration
    private var _listaJuegos = MutableStateFlow(mutableStateListOf<Juego>())
    var listaJuegos = _listaJuegos.asStateFlow()

    fun crearListener() {
        ListenerReg = conexion.collection("Videojuegos").addSnapshotListener { datos, error ->
            if (error == null) {
                // ¿Qué cambios ha habido en la BBDD?
                datos?.documentChanges?.forEach { cambio ->
                    var nuevoJuego = cambio.document.toObject<Juego>()
                    if (cambio.type == DocumentChange.Type.ADDED) {
                        // añadimos elemento a la lista UI
                        nuevoJuego.idJuego = cambio.document.id
                        _listaJuegos.value.add(nuevoJuego)
                    } else if (cambio.type == DocumentChange.Type.REMOVED) {
                        // borramos elemento de la lista UI
                        _listaJuegos.value.remove(nuevoJuego)
                    } else if (cambio.type == DocumentChange.Type.MODIFIED) {
                        val juegoModificado = cambio.document.toObject<Juego>()
                        juegoModificado.idJuego = cambio.document.id
                        val index =
                            _listaJuegos.value.indexOfFirst { it.idJuego == juegoModificado.idJuego }
                        if (index != -1) {
                            _listaJuegos.value[index] = juegoModificado
                        }
                    }

                }
            }
        }
    }

    fun borrarListener() {
        ListenerReg.remove()
    }

    fun añadirJuego() {
        var nuevo = Juego(
            "PEGI 18",
            "CD Projekt Red",
            "CD Projekt Red",
            Timestamp.now(),
            listOf("Rol"),
            listOf("Español de España", "Inglés", "Francés"),
            29.99,
            93,
            "The Witcher 3: Wild Hunt"
        )
        conexion.collection("Videojuegos").add(nuevo)
    }

    fun borrarJuego(juegoABorrar: Juego) {
        conexion.collection("Videojuegos").document(juegoABorrar.idJuego).delete()
        _listaJuegos.value.remove(juegoABorrar)
    }

    fun actualizar(juegoCambiar: Juego) {
        //conexion.collection("Juego").document(juegoCambiar.idJuego).set(juegoCambiar)
        conexion.collection("Videojuegos").document(juegoCambiar.idJuego).update("precio", 25.99)
    }

}