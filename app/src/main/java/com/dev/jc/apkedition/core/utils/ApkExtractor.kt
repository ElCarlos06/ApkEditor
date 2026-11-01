package com.dev.jc.apkedition.core.utils

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object ApkExtractor {

    /**
     * Extrae/Copia el APK de una aplicación instalada a la carpeta de trabajo interna de la app.
     * Retorna el archivo File del APK copiado.
     */
    suspend fun extractInstalledApk(context: Context, sourceApkPath: String, outputFileName: String): File =
        withContext(Dispatchers.IO) {
            val workingDir = File(context.cacheDir, "extracted_apks").apply {
                if (!exists()) mkdirs()
            }

            // Archivo de destino, ej: "com.whatsapp.apk"
            val destinationFile = File(workingDir, "$outputFileName.apk")

            val sourceFile = File(sourceApkPath)
            FileInputStream(sourceFile).use { input ->
                FileOutputStream(destinationFile).use { output ->
                    input.copyTo(output)
                }
            }

            destinationFile
        }

    /**
     * Copia un archivo APK seleccionado desde el almacenamiento (usando su Uri de SAF)
     * a la carpeta de trabajo interna para poder procesarlo.
     */
    suspend fun copyApkFromUri(context: Context, uri: Uri, outputFileName: String = "selected_app.apk"): File =
        withContext(Dispatchers.IO) {
            val workingDir = File(context.cacheDir, "imported_apks").apply {
                if (!exists()) mkdirs()
            }

            val destinationFile = File(workingDir, outputFileName)

            context.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(destinationFile).use { output ->
                    input.copyTo(output)
                }
            } ?: throw IllegalStateException("No se pudo abrir el archivo desde la URI")

            destinationFile
        }
}
