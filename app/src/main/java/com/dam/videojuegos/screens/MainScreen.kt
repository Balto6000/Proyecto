package com.dam.videojuegos.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dam.videojuegos.shared.ViewModelFirebase

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {

    val viewModelFirebase: ViewModelFirebase = viewModel()

    DisposableEffect(key1 = viewModelFirebase) {
        viewModelFirebase.crearListener()
        onDispose { viewModelFirebase.borrarListener() }
    }

    val listaJuegosUI = viewModelFirebase.listaJuegos.collectAsState().value

    Column {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(listaJuegosUI) {
                Text(modifier = Modifier.combinedClickable(
                    onClick = { viewModelFirebase.borrarJuego(it) },
                    onLongClick = { viewModelFirebase.actualizar(it) }
                ), text = it.toString())
            }
        }
        Button(onClick = { viewModelFirebase.añadirJuego() }) {
            Text(text = "$$$$$")
        }
    }

}