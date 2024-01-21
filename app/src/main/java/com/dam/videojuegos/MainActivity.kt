package com.dam.videojuegos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dam.videojuegos.shared.ViewModelFirebase
import com.dam.videojuegos.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Principal() {

    val viewModelFirebase: ViewModelFirebase = viewModel()

    DisposableEffect(key1 = viewModelFirebase) {
        viewModelFirebase.crearListener()
        onDispose { viewModelFirebase.borrarListener() }
    }

    var listaJuegosUI = viewModelFirebase.listaJuegos.collectAsState().value

    Column {
        LazyColumn() {
            items(listaJuegosUI) {
                Text(modifier = Modifier.combinedClickable (
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