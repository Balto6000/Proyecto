package com.dam.videojuegos.shared

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.dam.videojuegos.modelo.Juego
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import java.util.Date

class ViewModelFirebase : ViewModel() {

    val conexion = FirebaseFirestore.getInstance()

    private lateinit var ListenerReg: ListenerRegistration
    private var _listaJuegos = MutableStateFlow(mutableStateListOf<Juego>())
    var listaJuegos = _listaJuegos.asStateFlow()

    fun crearListener() {
        if (!::ListenerReg.isInitialized) {

            ListenerReg = conexion.collection("Videojuegos").addSnapshotListener { datos, error ->
                if (error == null) {
                    datos?.documentChanges?.forEach { cambio ->
                        var nuevoJuego = cambio.document.toObject<Juego>()
                        if (cambio.type == DocumentChange.Type.ADDED) {
                            nuevoJuego.idJuego = cambio.document.id
                            _listaJuegos.value.add(nuevoJuego)
                        } else if (cambio.type == DocumentChange.Type.REMOVED) {
                            nuevoJuego.idJuego = cambio.document.id
                            val index =
                                _listaJuegos.value.indexOfFirst { it.idJuego == nuevoJuego.idJuego }
                            if (index != -1) {
                                _listaJuegos.value.removeAt(index)
                            }
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
    }

    fun borrarListener() {
        ListenerReg.remove()
    }

    fun añadirJuego() {
        var nuevo = Juego(
            "PEGI 3",
            listOf("Playground Games"),
            "Explora los vibrantes paisajes del mundo abierto de México y disfruta de una diversión sin límites al volante de los mejores coches del mundo. Conquista la escarpada Sierra Nueva en la experiencia definitiva del rally de Horizon.",
            listOf("Xbox Game Studios"),
            Date(2021 - 1900, 11, 9),
            listOf("Acción", "Aventura", "Carreras", "Simuladores", "Deportes"),
            listOf(
                "Español de España",
                "Inglés",
                "Francés",
                "Italiano",
                "Alemán",
                "Checo",
                "Húngaro",
                "Japonés",
                "Coreano",
                "Polaco",
                "Portugués de Brasil",
                "Ruso",
                "Chino simplificado",
                "Español de Hispanoamérica",
                "Chino tradicional",
                "Turco"
            ),
            59.99,
            92,
            "Forza Horizon 5",
            "https://firebasestorage.googleapis.com/v0/b/proyectodam-5d94d.appspot.com/o/forza-horizon-5-logo-B7E05DB263-seeklogo.com.png?alt=media&token=04b6de17-72f3-44d5-bfaa-b30ee428555e"
        )
        conexion.collection("Videojuegos").add(nuevo)
    }

    fun borrarJuego(juegoABorrar: Juego) {
        conexion.collection("Videojuegos").document(juegoABorrar.idJuego).delete()
    }

    fun actualizar(juegoCambiar: Juego) {
        //conexion.collection("Videojuegos").document(juegoCambiar.idJuego).set(juegoCambiar)
        conexion.collection("Videojuegos").document(juegoCambiar.idJuego)
            .update("precio", 25.99)
    }

    fun obtenerJuegoPorId(juegoId: String?): Juego? {
        return listaJuegos.value.find { it.idJuego == juegoId }
    }

}