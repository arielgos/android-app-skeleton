package com.arielgos.mpos

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arielgos.mpos.core.DbHelper
import com.arielgos.mpos.core.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestPermissions()
        dbHelper = DbHelper(this@MainActivity)
        val db = dbHelper.writableDatabase
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }

    private fun requestPermissions() {
        val permissionsToRequest = mutableListOf<String>()
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
            packageInfo.requestedPermissions?.forEach { permission ->
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(permission)
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Logger.e("Error getting package info", e)
            return
        }

        if (permissionsToRequest.isNotEmpty()) {
            requestMultiplePermissionsLauncher.launch(permissionsToRequest.toTypedArray())
        } else {
            Logger.d("All permissions already granted.")
        }
    }

    private val requestMultiplePermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.entries.forEach {
            Logger.d("Permission: ${it.key}, Granted: ${it.value}")
            if (!it.value) {
                AlertDialog.Builder(this).setTitle("Permission Required").setMessage("Permission ${it.key} is mandatory for the app to function properly. Please grant the permission in settings.").setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }.setCancelable(false).show()
            }
        }
    }
}