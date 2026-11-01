package com.dev.jc.apkedition.data.model

import android.graphics.drawable.Drawable

data class AppInfo(
    val appName: String,                //Nombre
    val packageName: String,            //Paquete
    val versionName: String,            //Vesion
    val versionCode: Long,              //Codigo numerico
    val apkPatch: String,               //Ruta del archivo
    val icon: Drawable?,                //Icono
    val isSystemApp: Boolean = false    //Es apk o ya esta insalada
)
