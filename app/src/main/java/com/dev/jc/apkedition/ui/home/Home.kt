package com.dev.jc.apkedition.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Home(){


    Column(modifier = Modifier.fillMaxSize()
        ) {


        Button(modifier = Modifier.fillMaxSize(), onClick = {} ) {
            Text("Seleccionar un Archivo APK")
        }

        Button(onClick = {} ) {
            Text("Seleccionar una Aplicación Instalada")
        }

        Button(onClick = {} ) {
            Text("Información de uso")
        }

        Button(onClick = {} ) {
            Text("Salir")
        }
    }
}

@Preview (showBackground = true)
@Composable
fun HomePreview() {
    Home()
}