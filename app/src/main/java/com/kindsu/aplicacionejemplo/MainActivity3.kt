package com.kindsu.aplicacionejemplo

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kindsu.aplicacionejemplo.databinding.ActivityMain3Binding

/*Crea una aplicación Android que permita al usuario seleccionar una fecha o una hora.
La elección debe guardarse utilizando SharedPreferences para que, al reiniciar la
aplicación, se muestre automáticamente la fecha u hora elegida.*/
class MainActivity3 : AppCompatActivity() {
    private lateinit var binding : ActivityMain3Binding
    private val preferencias by lazy {
        getSharedPreferences("archivoFecha", Context.MODE_PRIVATE)
    } //By lazy nos hace que el bloque no se ejecutará hasta que no se use
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var fecha = preferencias.getString("fecha", "") //Obtener la fecha guardada anteriormente
        // el "" se deja vacio por si es la primera vez que se inicia
        // que tome un valor
        binding.etFechaGuardada.text = fecha //Mostrar la fecha que hemos recogido
        initListeners()
    }

    //Funcion para crear el dialog de la fecha
    private fun showDatePickerDialog(){
        var editor = preferencias.edit()
        //Recoge los valores del dialog y los muestra en el editText
        val datePicker = DatePickerFragment {day, month, year ->
            // Formatea la fecha a string
            val fecha = "$day/${month + 1}/$year"
            binding.etFecha.setText(fecha) //Muestro la fecha

            editor.putString("fecha", fecha) //Guardo la fecha nueva en el archivo
            editor.apply()
        }
        // Muestra el dialog de seleccion de fecha
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun formatDate(date : Calendar) : String{
        val day = date.get(Calendar.DAY_OF_MONTH)
        val month = date.get(Calendar.MONTH) + 1
        val year = date.get(Calendar.YEAR)
        return "$day/$month/$year"
    }

    private fun initListeners(){
        binding.etFecha.setOnClickListener(){
            showDatePickerDialog() //Mostrar dialog de fecha
        }
        binding.btnEjercicio1.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnEjercicio2.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

}