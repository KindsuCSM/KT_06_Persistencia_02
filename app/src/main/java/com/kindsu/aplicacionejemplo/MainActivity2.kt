package com.kindsu.aplicacionejemplo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kindsu.aplicacionejemplo.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    /*Crea una aplicación Android que permita al usuario responder cada día una pregunta.
    Si la respuesta es acertada, debe incrementarse los puntos obtenidos.
    El puntaje debe guardarse utilizando SharedPreferences para que, al reiniciar
    la aplicación, recuerde los puntos obtenidos hasta el momento.*/
    private lateinit var binding: ActivityMain2Binding
    private val preferencias by lazy {
        getSharedPreferences("preguntasGuardadas", Context.MODE_PRIVATE)
    } //By lazy nos hace que el bloque no se ejecutará hasta que no se use
    private var contador = 0
    private var puntuacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Obtener los resultados guardados
        contador = preferencias.getInt("contador", 0)
        puntuacion = preferencias.getInt("puntuacion", 0)

        // inicializar las listas
        val preguntas = inicializarPreguntas()
        val respuestas = inicializarRespuestas()

        //Ponerle el texto a los TextView
        binding.tvPregunta.text = preguntas[contador]
        binding.tvPuntuacion.text = puntuacion.toString()

        //Listener btnEnviar
        binding.btnEnviar.setOnClickListener {
            //Obtener la respuesta que ha introducido el usuario en una variable sin espacios por delante o por detras y en minusculas
            val respuesta = binding.etRespuesta.text.toString().trim().lowercase()

            //Si la respuesta introducida coincide con la respuesta guardada en la lista asociada al numero del contador hace:
            if (respuesta == respuestas[contador]) {
                //Suma 10 puntos a la puntuacion y lo muestra en la pantalla
                puntuacion += 10
                binding.tvPuntuacion.text = puntuacion.toString()
                //Hace un toast para que el usuario sepa que es correcta o incorrecta
                Toast.makeText(this, "Correcto!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrecto. Intenta de nuevo.", Toast.LENGTH_SHORT).show()
            }

            //tras comprobar la respuesta, la borramos del editText y sumamos 1 al contador
            binding.etRespuesta.text.clear()
            contador++

            //Si el contador llega al maximo(size de la lista), se reinician contadores, sino sigue a la siguiente
            if (contador < preguntas.size) {
                binding.tvPregunta.text = preguntas[contador]
            } else {
                contador = 0
                puntuacion = 0
                binding.tvPregunta.text = ContextCompat.getString(this, R.string.acertar)
            }

            //guardamos el contador y la puntuacion dentro del archivo con diferentes nombres
            val editor = preferencias.edit()
            editor.putInt("contador", contador)
            editor.putInt("puntuacion", puntuacion)
            editor.apply() //Aplicamos
        }

        binding.btnEjercicio1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnEjercicio3.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    fun inicializarRespuestas(): List<String> {
        return listOf(
            "parís",
            "italia",
            "américa del sur",
            "el río amazonas",
            "francia",
            "océano pacífico",
            "egipto",
            "rusia",
            "isla de pascua (chile)",
            "tokio",
            "george washington",
            "reino unido",
            "imperio romano",
            "napoleón bonaparte",
            "1989",
            "cristóbal colón",
            "inca",
            "china",
            "miguel ángel",
            "1945",
            "marte",
            "corazón",
            "nitrógeno",
            "mamífero",
            "fotosíntesis",
            "calcio",
            "h2o",
            "energía solar",
            "albert einstein",
            "saturno",
            "camaleón",
            "guitarra",
            "octubre",
            "vino",
            "tenis",
            "iron man",
            "naranja",
            "canguro",
            "chicle",
            "rosa",
            "x",
            "30",
            "triángulo",
            "72",
            "3.14",
            "cero",
            "49",
            "pentágono",
            "50",
            "75",
            "pikachu",
            "frozen",
            "el señor de los anillos",
            "ed sheeran",
            "johnny depp",
            "reino unido",
            "fútbol",
            "café",
            "neil armstrong",
            "chino mandarín",
            "océano ártico",
            "áfrica",
            "china",
            "murciélago",
            "antártida",
            "gabriel garcía márquez",
            "1914",
            "los andes",
            "china",
            "parís",
            "vaticano",
            "viola",
            "agave",
            "japón",
            "pinocho",
            "j.k. rowling",
            "argentina",
            "violín",
            "áfrica"
        )
    }

    fun inicializarPreguntas(): List<String> {
        return listOf(
            "¿Cuál es la capital de Francia?",
            "¿Qué país tiene forma de bota?",
            "¿En qué continente está Brasil?",
            "¿Cuál es el río más largo del mundo?",
            "¿Qué país es famoso por la Torre Eiffel?",
            "¿Cuál es el océano más grande del mundo?",
            "¿Dónde están las pirámides de Giza?",
            "¿Cuál es el país más grande del mundo?",
            "¿Qué isla es famosa por sus estatuas moái?",
            "¿Cuál es la capital de Japón?",
            "¿Quién fue el primer presidente de los Estados Unidos?",
            "¿En qué país comenzó la Revolución Industrial?",
            "¿Qué imperio construyó el Coliseo?",
            "¿Quién lideró Francia durante las Guerras Napoleónicas?",
            "¿En qué año cayó el Muro de Berlín?",
            "¿Quién descubrió América en 1492?",
            "¿Qué civilización construyó Machu Picchu?",
            "¿Cuál fue el primer país en usar papel moneda?",
            "¿Quién pintó la Capilla Sixtina?",
            "¿En qué año terminó la Segunda Guerra Mundial?",
            "¿Qué planeta es conocido como el planeta rojo?",
            "¿Qué órgano bombea sangre en el cuerpo?",
            "¿Cuál es el gas más abundante en la atmósfera terrestre?",
            "¿Qué tipo de animal es una ballena?",
            "¿Cómo se llama el proceso en el que las plantas producen oxígeno?",
            "¿Qué mineral es necesario para formar huesos fuertes?",
            "¿Cuál es la fórmula química del agua?",
            "¿Qué tipo de energía utiliza el Sol?",
            "¿Cómo se llama el científico que desarrolló la teoría de la relatividad?",
            "¿Qué planeta tiene los anillos más grandes?",
            "¿Qué animal es conocido por su habilidad para cambiar de color?",
            "¿Qué instrumento musical tiene seis cuerdas?",
            "¿En qué mes se celebra Halloween?",
            "¿Qué bebida se hace a partir de uvas fermentadas?",
            "¿Qué deporte se juega en Wimbledon?",
            "¿Qué superhéroe es conocido como el Hombre de Hierro?",
            "¿Qué fruta tiene el nombre de un color?",
            "¿Qué animal es el símbolo de Australia?",
            "¿Qué dulce es conocido como 'chicle'?",
            "¿Qué flor es símbolo del amor?",
            "¿Cuál es el número romano para 10?",
            "¿Qué número es el doble de 15?",
            "¿Qué forma tiene tres lados?",
            "¿Cuánto es 9 por 8?",
            "¿Cuál es el valor de pi con dos decimales?",
            "¿Qué número no tiene valor negativo?",
            "¿Cuál es el cuadrado de 7?",
            "¿Cómo se llama un polígono con cinco lados?",
            "¿Cuál es la mitad de 100?",
            "¿Cuánto es 100 menos 25?",
            "¿Qué animal es conocido como Pikachu?",
            "¿Qué película tiene personajes llamados Elsa y Anna?",
            "¿Qué saga tiene un personaje llamado Frodo?",
            "¿Quién canta 'Shape of You'?",
            "¿Qué actor interpreta a Jack Sparrow?",
            "¿En qué país nació el fútbol?",
            "¿Cuál es el deporte más popular del mundo?",
            "¿Qué bebida tiene cafeína?",
            "¿Quién fue el primer hombre en pisar la Luna?",
            "¿Cuál es la lengua más hablada del mundo?",
            "¿Qué océano es el más pequeño del mundo?",
            "¿Qué continente tiene más países?",
            "¿Qué país tiene más habitantes del mundo?",
            "¿Qué animal es conocido por su capacidad para volar y cazar en la oscuridad?",
            "¿Qué continente tiene el desierto más grande del mundo?",
            "¿Quién escribió 'Cien años de soledad'?",
            "¿En qué año comenzó la Primera Guerra Mundial?",
            "¿Cómo se llama la cadena montañosa más grande del mundo?",
            "¿En qué país se encuentra la Muralla China?",
            "¿En qué ciudad se celebran los Juegos Olímpicos de 2024?",
            "¿Cuál es el país más pequeño del mundo?",
            "¿Qué instrumento musical se toca con cuerdas y se coloca sobre el hombro?",
            "¿Qué planta se usa para hacer tequila?",
            "¿En qué país se originó el sushi?",
            "¿Qué famoso personaje de Disney tiene una nariz que crece cuando dice mentiras?",
            "¿Qué famoso autor escribió 'Harry Potter'?",
            "¿En qué país nació el tango?",
            "¿Qué instrumento se toca con un arco y tiene cuatro cuerdas?",
            "¿En qué continente está el desierto del Sahara?"
        )
    }
}