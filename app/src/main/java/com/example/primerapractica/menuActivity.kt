package com.example.primerapractica

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable.Creator
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class menuActivity : AppCompatActivity() {
    private lateinit var crvHola: CardView
    private lateinit var crvImc : CardView
    private lateinit var crvGrados : CardView
    private lateinit var crvMoneda : CardView
    private lateinit var crvCotizacion : CardView
    private lateinit var crvSalir : CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        iniciarComponentes()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        crvHola = findViewById(R.id.crvHola) as CardView
        crvImc = findViewById(R.id.crvImc) as CardView
        crvGrados = findViewById(R.id.crvConversion) as CardView
        crvMoneda = findViewById(R.id.crvMoneda) as CardView
        crvCotizacion = findViewById(R.id.crvCotizacion) as CardView
        crvSalir = findViewById(R.id.crvSalir) as CardView

    }

    fun eventosClic(){
        crvHola.setOnClickListener(View.OnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        })
        crvImc.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, imcActivity::class.java)
            startActivity(intent)
        })
        crvGrados.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, activity_grados::class.java)
            startActivity(intent)
        })
        crvMoneda.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, monedaActivity::class.java)
            startActivity(intent)
        })

        crvCotizacion.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, activity_cliente::class.java)
            startActivity(intent)
        })

        crvSalir.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("SALIR")
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