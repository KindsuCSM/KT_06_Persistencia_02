package com.kindsu.aplicacionejemplo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kindsu.aplicacionejemplo.databinding.ActivityMain3Binding
import android.icu.util.Calendar

/*Crea una aplicación Android que permita al usuario seleccionar una fecha o una hora.
La elección debe guardarse utilizando SharedPreferences para que, al reiniciar la
aplicación, se muestre automáticamente la fecha u hora elegida.*/
class MainActivity3 : AppCompatActivity() {
    private lateinit var binding : ActivityMain3Binding
    private val preferencias by lazy {
        getSharedPreferences("archivoFecha", Context.MODE_PRIVATE)
    }
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
        var fecha = preferencias.getString("fecha", "")

        binding.etFechaGuardada.setText(fecha)

        initListeners()



    }
    private fun showDatePickerDialog(){
        var editor = preferencias.edit()
        //Recoge los valores del dialog y los muestra en el editText
        val datePicker = DatePickerFragment {day, month, year ->
            val fecha = "$day/${month + 1}/$year"
            binding.etFecha.setText(fecha)

            editor.putString("fecha", fecha)
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

    private fun onDateSelected(day : Int, month : Int, year : Int){

    }

    private fun initListeners(){
        binding.etFecha.setOnClickListener(){
            showDatePickerDialog()

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