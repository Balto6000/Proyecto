package com.dam.videojuegos.screens

import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dam.videojuegos.R
import com.dam.videojuegos.ui.theme.AzulF
import com.dam.videojuegos.ui.theme.AzulO
import com.dam.videojuegos.ui.theme.Rosa
import androidx.compose.material3.DropdownMenu as DropdownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGame(navController: NavController){
    var texto by remember { mutableStateOf(TextFieldValue()) }
    var expandedGenero by remember {mutableStateOf(false)}
    var expandedIdioma by remember {mutableStateOf(false)}
    var state = rememberDatePickerState()

    val generos = listOf(
        "Acción",
        "Aventura",
        "Estrategia",
        "RPG (Rol de acción)",
        "FPS (Disparos en primera persona)",
        "TPS (Disparos en tercera persona)",
        "Deportes",
        "Carreras",
        "Puzzle",
        "Plataforma",
        "Survival",
        "Sigilo",
        "Simulación",
        "Horror",
        "Ciencia ficción",
        "Fantasía",
        "Mundo abierto",
        "Multijugador en línea",
        "Cooperativo",
        "Competitivo",
        "Exploración",
        "Educacional",
        "Casual",
        "Indie",
        "Arte",
        "Música",
        "Narrativo",
        "Sandbox"
    )
    var seleccionGenero by remember { mutableStateOf(generos[0])}
    val idiomas = listOf(
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
    )

    var seleccionIdioma by remember { mutableStateOf(idiomas[0])}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AzulO)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp))   {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier
                    .clickable{ navController.popBackStack() }
            )
            Icon(painter = painterResource(id = R.drawable.bglogo),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 270.dp)
                    .size(70.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "PARA AÑADIR UN JUEGO RELLENE LOS SIGUIENTES DATOS", color = Color.White,
                textAlign = TextAlign.Center)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = texto,
                        onValueChange = { newText ->
                            texto = newText
                        },
                        label = { Text("Titulo del juego", color = Color.White) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AzulF,
                            unfocusedLabelColor = AzulF,
                            focusedLabelColor = AzulF,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = CutCornerShape(15.dp)
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = texto,
                        onValueChange = { newText ->
                            texto = newText
                        },
                        label = { Text("Descripcion", color = Color.White) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AzulF,
                            unfocusedLabelColor = AzulF,
                            focusedLabelColor = AzulF,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = CutCornerShape(15.dp)
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = texto,
                        onValueChange = { newText ->
                            texto = newText
                        },
                        label = { Text("Desarrollador", color = Color.White) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AzulF,
                            unfocusedLabelColor = AzulF,
                            focusedLabelColor = AzulF,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = CutCornerShape(15.dp)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = texto,
                        onValueChange = { newText ->
                            texto = newText
                        },
                        label = { Text("Editor", color = Color.White) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AzulF,
                            unfocusedLabelColor = AzulF,
                            focusedLabelColor = AzulF,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = CutCornerShape(15.dp)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = texto,
                        onValueChange = { newText ->
                            texto = newText
                        },
                        label = { Text("Editor", color = Color.White) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AzulF,
                            unfocusedLabelColor = AzulF,
                            focusedLabelColor = AzulF,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = CutCornerShape(15.dp)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = texto,
                        onValueChange = { newText ->
                            texto = newText
                        },
                        label = { Text("Precio", color = Color.White) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AzulF,
                            unfocusedLabelColor = AzulF,
                            focusedLabelColor = AzulF,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = CutCornerShape(15.dp)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = texto,
                        onValueChange = { newText ->
                            texto = newText
                        },
                        label = { Text("Puntuacion", color = Color.White) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AzulF,
                            unfocusedLabelColor = AzulF,
                            focusedLabelColor = AzulF,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = CutCornerShape(15.dp)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = texto,
                        onValueChange = { newText ->
                            texto = newText
                        },
                        label = { Text("Clasificacion", color = Color.White) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AzulF,
                            unfocusedLabelColor = AzulF,
                            focusedLabelColor = AzulF,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = CutCornerShape(15.dp)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Seleccione los generos del juego", color = Color.White)
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .clickable { expandedGenero = true }
                    .padding(20.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = seleccionGenero)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = expandedGenero,
                        onDismissRequest = { expandedGenero = false }
                    ) {
                        generos.forEach { genero ->
                            DropdownMenuItem(
                                text = { Text(text = genero)}, onClick = {
                                    seleccionGenero = genero
                                })

                        }
                    }
                }

            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Seleccione los idiomas del juego", color = Color.White)
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .clickable { expandedIdioma = true }
                    .padding(20.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = seleccionIdioma)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = expandedIdioma,
                        onDismissRequest = { expandedIdioma = false },
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                    ) {
                        idiomas.forEach { idioma ->
                            DropdownMenuItem(
                                text = { Text(text = idioma)}, onClick = {
                                    seleccionIdioma = idioma
                                })

                        }
                    }
                }

            }

            item {
                Row(modifier = Modifier
                    .padding(20.dp)){
                    Text(text = "Seleccione la fecha de la carrera", color = Color.White)
                }
                DatePicker(state = state )
            }

            item{
                Row {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .background(
                                color = Rosa,
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Text(text = "Aceptar")
                    }
                }
            }

        }


    }
}






