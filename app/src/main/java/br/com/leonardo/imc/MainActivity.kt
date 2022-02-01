package br.com.leonardo.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import br.com.leonardo.imc.extensions.format
import br.com.leonardo.imc.watchers.DecimalTextWatcher

class MainActivity : AppCompatActivity() {

    private lateinit var btCalcular: Button
    private lateinit var etPeso: EditText
    private lateinit var etAltura: EditText
    private lateinit var tvIMC: TextView
    private lateinit var tvIMCStatus: TextView
    private lateinit var ivIMCStatus: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()

        setUpListener()
    }

    fun setUpView() {
        etPeso = findViewById(R.id.etPeso)
        btCalcular = findViewById(R.id.btCalcular)
        etAltura = findViewById(R.id.etAltura)
        tvIMC = findViewById(R.id.tvIMC)
        tvIMCStatus = findViewById(R.id.tvIMCStatus)
        ivIMCStatus = findViewById(R.id.ivIMCStatus)
    }

    private fun setUpListener() {
        btCalcular.setOnClickListener {
            calcular()
        }

        etPeso.addTextChangedListener(DecimalTextWatcher(etPeso, 1))
        etAltura.addTextChangedListener(DecimalTextWatcher(etAltura, 2))
    }

    private fun calcular() {
        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        val imc = peso / (altura * altura)

        when (imc) {
            in 0.0..18.5 -> configuraIMC(
                imc, R.drawable.masc_abaixo,
                R.string.magreza
            )
            in 18.6..24.9 -> configuraIMC(
                imc, R.drawable.masc_ideal,
                R.string.peso_normal
            )
            in 25.0..29.9 -> configuraIMC(
                imc, R.drawable.masc_sobre,
                R.string.sobre_peso
            )
            in 30.0..34.9 -> configuraIMC(
                imc, R.drawable.masc_obeso,
                R.string.obesidade_grau_i
            )
            in 35.0..39.9 -> configuraIMC(
                imc, R.drawable.masc_extremo_obeso,
                R.string.obesidade_grau_ii
            )
            else -> configuraIMC(
                imc, R.drawable.masc_extremo_obeso,
                R.string.obesidade_grau_iii
            )
        }
    }

    private fun configuraIMC(imc: Double, imagemId: Int, stringId: Int) {
        tvIMC.text = getString(R.string.seu_imc, imc.format(2))

        tvIMCStatus.text = getString(stringId)

        ivIMCStatus.setImageDrawable(ContextCompat.getDrawable(this, imagemId))

    }

}