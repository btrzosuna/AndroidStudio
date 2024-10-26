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
    private lateinit var txtNombre : EditText
    private lateinit var txtSaludo : TextView
    private lateinit var btnPulsar : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        iniciarComponentes()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    public fun iniciarComponentes(){
        txtNombre = findViewById(R.id.txtNombre) as EditText
        txtSaludo = findViewById(R.id.txtSaludo) as TextView
        btnPulsar = findViewById(R.id.btnPulsar) as Button
        btnLimpiar = findViewById(R.id.btnLimpiar) as Button
        btnCerrar = findViewById(R.id.btnCerrar) as Button
    }
    public fun eventosClic(){
        btnPulsar.setOnClickListener(View.OnClickListener {

            if(txtNombre.text.toString().contentEquals(charSequence = "")){
                Toast.makeText(this,"Falto capturar tu nombre",
                    Toast.LENGTH_SHORT).show()
            }
            else {
                var strNombre: String = txtNombre.text.toString()
                txtSaludo.text = "Hola " +strNombre+", Como estas?"

            }
        })

        btnLimpiar.setOnClickListener(View.OnClickListener {
            //Limpiar un TextView
            txtSaludo.text=""
            //Limpiar EditText
            txtNombre.setText("")
        })

        btnCerrar.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}