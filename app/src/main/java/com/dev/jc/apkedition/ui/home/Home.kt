package com.dev.jc.apkedition.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.jc.apkedition.ui.theme.ApkEditionTheme

@Composable
fun Home(
    onSelectApkFile: () -> Unit,
    onSelectInstalledApp: () -> Unit,
    onShowHelp: () -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título de la app (cambiar a icon depues posiblemente)
        Text(
            text = "APK Editor",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Inspecciona y edita aplicaciones",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(40.dp))

        // CAMBIAR ESTRUCTURA DE BOTONES (4 PEGADOS CON ICONOS, TABLERO/PLANO)
        // Botón 1: Seleccionar archivo APK
        Button(
            onClick = onSelectApkFile,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Seleccionar un archivo APK", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón 2: Seleccionar App instalada
        Button(
            onClick = onSelectInstalledApp,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Seleccionar app instalada", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón 3: Información
        OutlinedButton(
            onClick = onShowHelp,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Información de uso", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón 4: Salir
        OutlinedButton(
            onClick = onExit,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Salir", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ApkEditionTheme {
        Home(
            onSelectApkFile = {},
            onSelectInstalledApp = {},
            onShowHelp = {},
            onExit = {}
        )
    }
}
