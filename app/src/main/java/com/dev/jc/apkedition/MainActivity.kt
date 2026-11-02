package com.dev.jc.apkedition

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.jc.apkedition.ui.home.Home
import com.dev.jc.apkedition.ui.theme.ApkEditionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApkEditionTheme {
                // Selector na-tivo de archivos (abre el explorador para elegir archivos .apk)
                val apkPickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    if (uri != null) {
                        Toast.makeText(this, "APK seleccionado: $uri", Toast.LENGTH_SHORT).show()
                        // Aquí traemos el archivo con ApkExtractor
                    }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(
                        modifier = Modifier.padding(innerPadding),
                        onSelectApkFile = {
                            // Filtro para mostrar solo archivos APK / binarios
                            apkPickerLauncher.launch("*/*")
                        },
                        onSelectInstalledApp = {
                            Toast.makeText(this, "Próximamente: Lista de apps", Toast.LENGTH_SHORT).show()
                        },
                        onShowHelp = {
                            Toast.makeText(this, "APK Editor v1.0", Toast.LENGTH_SHORT).show()
                        },
                        onExit = {
                            finish()
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApkEditionTheme {
        Home(
            onSelectApkFile = {},
            onSelectInstalledApp = {},
            onShowHelp = {},
            onExit = {}
        )
    }
}