package com.kindsu.aplicacionejemplo

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment

/*Creamos una clase DatePickerFragment que extiende de DialoFragment e implementa
* DatePickerDialog.OnDateSetListener que recibirá dia, mes y año y devuelve Unit(formato fecha)*/
class DatePickerFragment(val listener : (day:Int, month:Int, year:Int) -> Unit) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    //Sobreescribinos el metodo de DialogFragment()
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        //Le asignamos los valores que introduce el usuario
        listener(day, month, year)
    }
    //Sobreescribimos el metodo del DialogFragment() que se ejecutará cuando se crea la pestaña
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Obtenemos los valores que introduce el usuario al clickar sobre una fecha
        val calendar = Calendar.getInstance() // Instanciamos clase calendario
        val year = calendar.get(Calendar.YEAR) // Obtenemos el año
        val month = calendar.get(Calendar.MONTH) // Obtenemos el mes
        val day = calendar.get(Calendar.DAY_OF_MONTH) // Obtenemos el dia

        //Instancia de DatePickerDialog con la fecha que introduce el usuario
        val picker = DatePickerDialog(activity as Context, this, year, month, day)

        return picker
    }
}