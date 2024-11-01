package com.example.primerapractica

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_grados : AppCompatActivity() {

    private lateinit var txtCelsius: EditText
    private lateinit var txtFahrenheit: EditText
    private lateinit var txtResultado: TextView
    private lateinit var btnConvertir: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private lateinit var spinner: Spinner
    private var conversionType: String = "Celsius a Fahrenheit"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grados)
        iniciarComponentes()
        eventosClic()
        configurarSpinner()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun iniciarComponentes() {
        txtCelsius = findViewById(R.id.txtCelsius)
        txtFahrenheit = findViewById(R.id.txtFahrenheit)
        txtResultado = findViewById(R.id.txtResultado)
        btnConvertir = findViewById(R.id.btnConvertir)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
        spinner = findViewById(R.id.spinnerConversion)
    }

    private fun configurarSpinner() {
        val opciones = arrayOf("Celsius a Fahrenheit", "Fahrenheit a Celsius")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                conversionType = opciones[position]
                txtCelsius.setText("")
                txtFahrenheit.setText("")
                txtResultado.text = "El resultado aparece aquí"

                // Mostrar u ocultar EditText según la selección
                when (conversionType) {
                    "Celsius a Fahrenheit" -> {
                        txtCelsius.visibility = View.VISIBLE
                        txtFahrenheit.visibility = View.GONE
                    }
                    "Fahrenheit a Celsius" -> {
                        txtCelsius.visibility = View.GONE
                        txtFahrenheit.visibility = View.VISIBLE
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun eventosClic() {
        // Botón convertir
        btnConvertir.setOnClickListener {
            when (conversionType) {
                "Celsius a Fahrenheit" -> {
                    if (txtCelsius.text.isEmpty()) {
                        Toast.makeText(this, "Introduce grados Celsius", Toast.LENGTH_SHORT).show()
                    } else {
                        val celsius = txtCelsius.text.toString().toFloatOrNull()
                        if (celsius != null) {
                            val resultFahrenheit = (celsius * 9 / 5) + 32
                            txtResultado.text = String.format("%.2f °F", resultFahrenheit)
                        }
                    }
                }
                "Fahrenheit a Celsius" -> {
                    if (txtFahrenheit.text.isEmpty()) {
                        Toast.makeText(this, "Introduce grados Fahrenheit", Toast.LENGTH_SHORT).show()
                    } else {
                        val fahrenheit = txtFahrenheit.text.toString().toFloatOrNull()
                        if (fahrenheit != null) {
                            val resultCelsius = (fahrenheit - 32) * 5 / 9
                            txtResultado.text = String.format("%.2f °C", resultCelsius)
                        }
                    }
                }
            }
        }

        // Botón limpiar
        btnLimpiar.setOnClickListener {
            txtResultado.text = ""
            txtCelsius.setText("")
            txtFahrenheit.setText("")
        }

        // Botón cerrar
        btnCerrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Conversor de Temperatura")
            builder.setMessage("¿Deseas salir?")
            builder.setPositiveButton("Aceptar") { _, _ -> finish() }
            builder.setNegativeButton("Cancelar", null)
            builder.show()
        }
    }

}