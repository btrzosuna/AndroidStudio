package com.example.primerapractica

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding

class MainActivity : AppCompatActivity() {
    private lateinit var lblSaludo: TextView
    private lateinit var txtNombre: EditText
    private lateinit var btnSaludar: Button
    private lateinit var btnPulsar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //iniciarComponentes()
        eventosBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){ v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

        fun eventosBotones(){
            btnSaludar.setOnClickListener(View.OnClickListener{
                var strNombre : String =""
                if (txtNombre.text.toString().contentEquals("")){
                    Toast.makeText(applicationContext, "Falto capturar el nombre", Toast.LENGTH_SHORT).show()
                }
                else{
                    strNombre ="Hola "+ txtNombre.toString() + "como estas?"
                    lblSaludo.text =strNombre
                }
            })
            btnLimpiar.setOnClickListener(View.OnClickListener{
                txtNombre.setText("")
                lblSaludo.setText("")
            })
            btnCerrar.setOnClickListener(View.OnClickListener{
                finish()
            })
        }

//        // Inicializar los elementos de la interfaz
//        editTextNombre = findViewById(R.id.editTextNombre)
//        btnPulsar = findViewById(R.id.btnPulsar)
//        btnLimpiar = findViewById(R.id.btnLimpiar)
//
//        // Configurar el botón "Pulsar"
//        btnPulsar.setOnClickListener(View.OnClickListener{
//            val nombre = editTextNombre.text.toString()
//            if (nombre.isNotBlank()) {
//                Toast.makeText(this, "Hola, ¿Cómo estás $nombre!", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Por favor, ingresa tu nombre.", Toast.LENGTH_SHORT).show()
//            }
//        })



//        // Configurar el botón "Limpiar"
//        btnLimpiar.setOnClickListener {
//            editTextNombre.setText("") // Limpia el EditText
//        }
//
//        // Configurar el botón "Limpiar"
//        btnCerrar.setOnClickListener(View.OnClickListener {
//           finish()
//        })
//
//
//

}
