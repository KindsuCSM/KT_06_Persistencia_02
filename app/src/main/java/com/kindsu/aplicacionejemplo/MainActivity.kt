package com.kindsu.aplicacionejemplo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kindsu.aplicacionejemplo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*Crea una aplicación Android que permita al usuario seleccionar el color de uno
    de sus componentes. La elección del color debe guardarse utilizando SharedPreferences
    para que, al reiniciar la aplicación, se aplique automáticamente el color seleccionado.*/

    private lateinit var binding: ActivityMainBinding
    private val preferencias by lazy {
        getSharedPreferences("colorGuardado", Context.MODE_PRIVATE)
    } //By lazy nos hace que el bloque no se ejecutará hasta que no se use

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cambiarColor(
            preferencias.getInt(
                "color",
                ContextCompat.getColor(this, R.color.default_color)
            )
        )
        addListeners()
    }

    fun guardarColor(color: Int) {
        val editor = preferencias.edit()
        editor.putInt("color", color)
        editor.apply()
    }

    fun addListeners() {
        var color: Int
        binding.btnAmarillo.setOnClickListener {
            color = ContextCompat.getColor(this, R.color.amarillo)
            cambiarColor(color)
            guardarColor(color)
        }
        binding.btnAzul.setOnClickListener {
            color = ContextCompat.getColor(this, R.color.azul)
            cambiarColor(color)
            guardarColor(color)
        }
        binding.btnMorado.setOnClickListener {
            color = ContextCompat.getColor(this, R.color.morado)
            cambiarColor(color)
            guardarColor(color)
        }
        binding.btnRosa.setOnClickListener {
            color = ContextCompat.getColor(this, R.color.rosa)
            cambiarColor(color)
            guardarColor(color)
        }
        binding.btnEjercicio2.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        binding.btnEjercicio3.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    fun cambiarColor(color: Int) {
        binding.crdCambiar.setCardBackgroundColor(color)
    }
}