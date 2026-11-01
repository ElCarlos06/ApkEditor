package com.dev.jc.apkedition.data.repository

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.dev.jc.apkedition.data.model.AppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val context: Context) {
    private val packageManager: PackageManager = context.packageManager

    /*
     Hay que sacar la lista de las apps intaladass, esto las saca alfabeticamente ejecutandose en un hilo secundario (Dispatchers.IO) para no congelar la UI.
     */
    suspend fun getInstalledApps(includeSystemApps: Boolean = false): List<AppInfo> = withContext(
        Dispatchers.IO) {
        val appList = mutableListOf<AppInfo>()
        // Flags para obtener la información de paquetes
        val flags = PackageManager.GET_META_DATA
        val installedPackages: List<PackageInfo> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getInstalledPackages(PackageManager.PackageInfoFlags.of(flags.toLong()))
        } else {
            @Suppress("DEPRECATION")
            packageManager.getInstalledPackages(flags)
        }
        for (pkg in installedPackages) {
            val appInfo = pkg.applicationInfo ?: continue
            // Comprobar si es una aplicación del sistema
            val isSystemApp = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
            // Si el usuario no quiere apps de sistema, las omitimos
            if (!includeSystemApps && isSystemApp) {
                continue
            }
            val name = packageManager.getApplicationLabel(appInfo).toString()
            val packageName = pkg.packageName
            val versionName = pkg.versionName ?: "N/A"
            val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                pkg.longVersionCode
            } else {
                @Suppress("DEPRECATION")
                pkg.versionCode.toLong()
            }
            val apkPath = appInfo.sourceDir // Ruta física al archivo base.apk en el dispositivo
            val icon = packageManager.getApplicationIcon(appInfo)
            appList.add(
                AppInfo(
                    appName = name,
                    packageName = packageName,
                    versionName = versionName,
                    versionCode = versionCode,
                    apkPatch = apkPath,
                    icon = icon,
                    isSystemApp = isSystemApp
                )
            )
        }
        // Ordenar alfabéticamente por nombre de la app (insensible a mayúsculas)
        appList.sortedBy { it.appName.lowercase() }
    }
}