package com.examples.newwidgetsimc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.examples.newwidgetsimc.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityResultadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initElements()

    }
    private fun initElements(){
        val b = intent.extras
        var estado = "" // or other values
        var resultado = ""
        var mensaje = ""
        if (b != null) estado = b.getString("estado")!!
        if(b!= null) resultado = b.getString("resultado")!!
        if(b!= null) mensaje = b.getString("mensaje")!!
        binding.resultadovalor.setText(resultado)
        binding.estadolabel.setText(estado)
        binding.mensajeFinal.setText(mensaje)

        binding.recalcular.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}