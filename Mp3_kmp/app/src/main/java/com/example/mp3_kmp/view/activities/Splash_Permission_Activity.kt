package com.example.mp3_kmp.view.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mp3_kmp.R

class Splash_Permission_Activity : AppCompatActivity() {

    private val REQUEST_PERMISSIONS = 12345

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val handler = Handler()
                handler.postDelayed({
                    val intent = Intent(baseContext, MenuActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out)
                    finish()
                }, 500)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__permission_)

        //Verificar se a permissão ja foi concedida
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS)


            //Caso as permissões ja estejam concendidas, colete os arquivos
        } else {

            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(baseContext, MenuActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out)
                finish()
            }, 1000)
        }
    }



}
