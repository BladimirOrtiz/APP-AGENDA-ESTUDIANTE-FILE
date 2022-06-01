package com.example.agendaestudiantefile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class Estudiante : AppCompatActivity() {
    //Declarar una variable cadena para almacenar la ruta relativa de la carpeta
    //dónde guardaremos el archivo. en este caso la carpeta será la de Descargas
    private lateinit var ruta : String
    //Declarar una variable cadena para el nombre del archivo
    private lateinit var nombreArchivo:String
    //Declarar la variable de referencia de tipo archivo
    private lateinit var archivo: File

    //Declaramos los objetos para los views
    private lateinit var nc : EditText
    private lateinit var nombre : EditText
    private  lateinit var carrera : Spinner
    private lateinit var semestre : Spinner
    private lateinit var grupoA : RadioButton
    private lateinit var grupoB : RadioButton
    //Declarar la variable de tipo EditText Multilínea. En este componente listo los registros
    private lateinit var edt : EditText

    private  lateinit var botonRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante)

        //Instanciamos los vies - Hacemos referencia
        nc= findViewById(R.id.txtNControl)
        nombre = findViewById(R.id.txtNombre)
        carrera= findViewById(R.id.cbxCarreras)
        semestre= findViewById((R.id.cbxSemestre))
        grupoA = findViewById(R.id.rbt_salaA)
        grupoB = findViewById(R.id.rbtGrupoB)
        edt = findViewById(R.id.txtRegistrosA)
        botonRegistrar = findViewById(R.id.btnRegistrar)

        //Inicializamos y creamos las variables
        ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .toString()
        //Asignar en una variable cadena el nombre del archivo
        nombreArchivo = "agenda_estudiantes.txt"
        //Asignar a la variable de referencia archivo la ruta y nombre de archivo. Es decir
        // apuntar hacia la ruta relativa donde se creará el archivo.
        archivo = File(ruta + "/" + nombreArchivo)
        //Apuntar hacia el componente txtRegistros que es el EditText en la interfaz


        //Verificar si existen registros y si los hay Mostrarlos en una vista multilínea
        if (archivo.exists()){
            var cadena: String = ""
            cadena = archivo.readText()
            edt.setText(cadena)
        }

        botonRegistrar.setOnClickListener(){
            fnRegistrar()
        }
       }
    fun fnRegistrar(){
        var cadena = nc.text.toString()+","+
                nombre.text.toString()+","+
                carrera.selectedItem+","+
                semestre.selectedItem+","

        cadena+= if (grupoA.isChecked) "A" else "B"
        cadena+="\n"
        //Manejo de archivos
        //La primera vez Si no existe el archivo lo creamos
        if(!archivo.exists()){
            archivo.createNewFile()
            println(archivo.path)
        }
        //Escribir datos en el archivo
        //Abrir el archivo
        // Creamos la variable que haga referencia al archivo
        val fileWrite : FileWriter = FileWriter(archivo,true)
        //Escribir en el archivo
        val escribir : BufferedWriter = BufferedWriter(fileWrite)
        escribir.write(cadena)
        //Cerrar el archivo
        escribir.close()
        fileWrite.close()
        // Mostrar los datos
        edt.append(cadena)
    }
    }
