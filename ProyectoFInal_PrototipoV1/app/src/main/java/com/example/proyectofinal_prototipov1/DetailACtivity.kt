package com.example.proyectofinal_prototipov1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailACtivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen() // Usa el SplashScreen
        super.onCreate(savedInstanceState)

        // Carga el layout de la pantalla de splash (no el MainActivity)
        setContentView(R.layout.activity_detail) // Asegúrate de que tienes un layout llamado activity_detail

        // Mantén el splash screen durante un tiempo (ejemplo de 4 segundos)
        screenSplash.setKeepOnScreenCondition { true }

        // Usa un Handler para retrasar la redirección sin congelar la UI
        Handler(Looper.getMainLooper()).postDelayed({
            // Redirige al MainActivity después del tiempo de espera
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Cierra el DetailACtivity para que no regrese al splash
        }, 4000) // 4 segundos de espera
    }
}
