package com.example.primerapractica

import android.os.Bundle
import android.view.View
import android.view.ViewParent
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
import com.google.android.material.button.MaterialButtonToggleGroup

class monedaActivity : AppCompatActivity() {
    private lateinit var txtCantidad : EditText
    private lateinit var spnConversion : Spinner
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button
    private lateinit var txtResultado : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_moneda)
        iniciarComponentes()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    public fun iniciarComponentes(){
        txtCantidad = findViewById(R.id.txtCantidad) as EditText
        spnConversion = findViewById(R.id.spnConversion) as Spinner
        btnCalcular = findViewById(R.id.btnCalcular) as Button
        btnLimpiar  = findViewById(R.id.btnLimpiar) as Button
        btnCerrar = findViewById(R.id.btnCerrar) as Button
        txtResultado = findViewById(R.id.txtResultado) as TextView

        //Llenar Spiner con una fuente de datos mediante un adaptador
        val items = resources.getStringArray(R.array.array_conversiones)
        val adapter = ArrayAdapter(this , android.R.layout.simple_list_item_1, items)
        spnConversion.adapter = adapter
    }

    public fun eventosClic(){
        var pos : Int = 0
        spnConversion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = parent?.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext,"Seleccionaste $item", Toast.LENGTH_SHORT).show()
                pos = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        btnCalcular.setOnClickListener(View.OnClickListener {
            //Valores de las monedas
            val dolara = resources.getString(R.string.dolar).toFloat()
            val dolarc = resources.getString(R.string.dolarc).toFloat()
            val euro = resources.getString(R.string.euro).toFloat()

            //Validar Cantidad
            if(txtCantidad.text.toString().contentEquals("")){
                Toast.makeText(applicationContext, "Falto capturar cantidad", Toast.LENGTH_SHORT).show()
                }
            else{
                var resultado : Float = 0.0f
                var cantidad : Float = 0.0f
                cantidad = txtCantidad.text.toString().toFloat()
                resultado = when (pos){
                    0 -> cantidad / dolara
                    1 -> cantidad / dolarc
                    2 -> cantidad / euro
                    3 -> cantidad * dolarc
                    4 -> cantidad * dolara
                    5 -> cantidad * euro
                    else -> 0.0f

                }
                txtResultado.text=resultado.toString()
            }

        })

        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtCantidad.setText("")
            txtResultado.setText("")
        })

        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Conversión")
            builder.setMessage("¿Deseas Salir?")
            builder.setPositiveButton("Aceptar"){
                    dialog, wich ->
                finish()
            }
            builder.setNegativeButton("Cancelar"){
                    dialog, wich ->

            }
            builder.show()
        })

    }

}