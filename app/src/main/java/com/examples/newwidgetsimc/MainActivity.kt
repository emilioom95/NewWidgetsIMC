package com.examples.newwidgetsimc

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.examples.newwidgetsimc.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var alturaValorM: Double = 0.00;
    private var esHombre = false
    private var esMujer = false;
    private val f1 = 2.25;
    private val f2 = 45;
    private val m1 = 2.7
    private val m2 = 47.75
    private var resultado = ""
    private var mensaje = ""
    private var estado = ""
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initElements()

    }

    private fun initElements() {
        binding.edadvalor.setText("0")
        binding.pesovalor.setText("0")
        binding.cardViewHombre.setCardBackgroundColor(Color.DKGRAY)
        binding.cardViewMujer.setCardBackgroundColor(Color.DKGRAY)

        binding.buttoncalcular.setOnClickListener{
            imc()
            val intent = Intent(this, ResultadoActivity::class.java)
            val b = Bundle()
            b.putString("estado", estado) //Your id
            b.putString("resultado", resultado)
            b.putString("mensaje", mensaje)
            intent.putExtras(b) //Put your id to your next Intent
            startActivity(intent)
            finish()
        }

        binding.cardViewHombre.setOnClickListener(View.OnClickListener {
            if (esHombre) {
                esHombre = false
                esMujer = false
                binding.cardViewHombre.setCardBackgroundColor(Color.DKGRAY)
                binding.cardViewMujer.setCardBackgroundColor(Color.DKGRAY)
            } else {
                esMujer = false
                esHombre = true
                binding.cardViewHombre.setCardBackgroundColor(Color.GRAY)
                binding.cardViewMujer.setCardBackgroundColor(Color.DKGRAY)
            }
        })

        binding.cardViewMujer.setOnClickListener(View.OnClickListener {
            if (esMujer) {
                esMujer = false
                esHombre = false
                binding.cardViewMujer.setCardBackgroundColor(Color.DKGRAY)
                binding.cardViewHombre.setCardBackgroundColor(Color.DKGRAY)
            } else {
                esMujer = true
                esHombre = false
                binding.cardViewMujer.setCardBackgroundColor(Color.GRAY)
                binding.cardViewHombre.setCardBackgroundColor(Color.DKGRAY)
            }
        })

        binding.buttonMasPeso.setOnClickListener {
            val pesoActual = Integer.parseInt(binding.pesovalor.text.toString())
            binding.pesovalor.setText((pesoActual + 1).toString())
        }

        binding.buttonMenosPeso.setOnClickListener {
            val peso = Integer.parseInt(binding.pesovalor.text.toString())
            binding.pesovalor.setText((peso - 1).toString())
        }

        binding.buttonMasEdad.setOnClickListener {
            val edadActual = Integer.parseInt(binding.edadvalor.text.toString())
            binding.edadvalor.setText((edadActual + 1).toString())
        }

        binding.buttonMenosEdad.setOnClickListener {
            val edadActual = Integer.parseInt(binding.edadvalor.text.toString())
            binding.edadvalor.setText((edadActual - 1).toString())
        }
        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                alturaValorM = java.lang.Double.parseDouble(progress.toString()) / 100;
                binding.alturaValor.setText(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    fun imc() {
        if (binding.pesovalor.text.toString().trim().isEmpty()) {
            binding.pesovalor.requestFocus()
            Toast.makeText(this, "Favor de ingresar el peso", Toast.LENGTH_LONG).show()
        } else if (binding.alturaValor.text.toString().trim().isEmpty()) {
            binding.alturaValor.requestFocus()
            Toast.makeText(this, "Favor de ingresar la estatura", Toast.LENGTH_LONG).show()
        } else {
            val kg: Float = java.lang.Float.parseFloat(binding.pesovalor.text.toString())
            val m: Float = java.lang.Float.parseFloat(binding.alturaValor.text.toString())/100
            val bmi = (kg / (m * m))
            resultado = String.format("%.2f", bmi).toDouble().toString()
            if (bmi < 18.0) {
                mensaje =  "Estas bajo para tu peso y altura"
                estado = "Bajo";
                //Toast.makeText(this, "Tu imc es " + bmi + "\nBajo peso", Toast.LENGTH_LONG).show()
            } else {
                if (bmi < 24.9) {
                    estado = "Normal"
                    mensaje = "Estas optimo para tu peso y altura"
                } else {
                    if (bmi < 29.9) {
                        estado = "Alto"
                        mensaje = "Esta alto para tu peso altura"

                    } else {
                        if (bmi < 34.9) {
                            estado = "Alto"
                            mensaje = "Esta alto para tu peso y altura"

                        } else {
                            if (bmi > 35) {
                                estado = "Alto"
                                mensaje = "Esta muy alto para tu peso y altura"
                            }
                        }
                    }
                }
            }
        }
    }

}