package com.example.prctica04_componentesdepaletabuttons

// MainActivity.kt
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val inventario = arrayOfNulls<RopaDeportiva>(15)
    private var posicionActual: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias de los componentes
        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val etMarca = findViewById<EditText>(R.id.etMarca)
        val etModelo = findViewById<EditText>(R.id.etModelo)
        val etCosto = findViewById<EditText>(R.id.etCosto)

        val rgTalla = findViewById<RadioGroup>(R.id.rgTalla)
        val cgColores = findViewById<ChipGroup>(R.id.cgColores)

        val btnRegistrar = findViewById<ImageButton>(R.id.btnRegistrar)
        val btnBuscar = findViewById<ImageButton>(R.id.btnBuscar)
        val btnEliminar = findViewById<ImageButton>(R.id.btnEliminar)
        val btnLimpiar = findViewById<ImageButton>(R.id.btnLimpiar)



        // Funcionalidad del botón registrar
        btnRegistrar.setOnClickListener {
            val codigo = etCodigo.text.toString()
            val marca = etMarca.text.toString()
            val modelo = etModelo.text.toString()
            val costo = etCosto.text.toString().toDoubleOrNull()
            val talla = when (rgTalla.checkedRadioButtonId) {
                R.id.rbChica -> "Chica"
                R.id.rbMediana -> "Mediana"
                R.id.rbGrande -> "Grande"
                else -> ""
            }
            val colores = getColoresSeleccionados(cgColores)

            if (codigo.isNotEmpty() && marca.isNotEmpty() && modelo.isNotEmpty() && costo != null && talla.isNotEmpty()) {
                registrarRopa(RopaDeportiva(codigo, marca, modelo, talla, colores, costo))
                limpiarCampos(etCodigo, etMarca, etModelo, etCosto, rgTalla, cgColores)
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Funcionalidad del botón buscar
        btnBuscar.setOnClickListener {
            val codigo = etCodigo.text.toString()
            buscarRopa(codigo, etMarca, etModelo, etCosto, rgTalla, cgColores)
        }

        // Funcionalidad del botón eliminar
        btnEliminar.setOnClickListener {
            val codigo = etCodigo.text.toString()
            eliminarRopa(codigo)
        }

        // Funcionalidad del botón limpiar
        btnLimpiar.setOnClickListener {
            limpiarCampos(etCodigo, etMarca, etModelo, etCosto, rgTalla, cgColores)
        }

    }

    private fun getColoresSeleccionados(cgColores: ChipGroup): Set<String> {
        val coloresSeleccionados = mutableSetOf<String>()
        for (i in 0 until cgColores.childCount) {
            val chip = cgColores.getChildAt(i) as Chip
            if (chip.isChecked) {
                coloresSeleccionados.add(chip.text.toString())
            }
        }
        return coloresSeleccionados
    }

    private fun registrarRopa(ropa: RopaDeportiva) {
        for (i in inventario.indices) {
            if (inventario[i] == null) {
                inventario[i] = ropa
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                return
            }
        }
        Toast.makeText(this, "No hay espacio disponible", Toast.LENGTH_SHORT).show()
    }

    private fun buscarRopa(codigo: String, etMarca: EditText, etModelo: EditText, etCosto: EditText, rgTalla: RadioGroup, cgColores: ChipGroup) {
        for (i in inventario.indices) {
            inventario[i]?.let { ropa ->
                if (ropa.codigo == codigo) {
                    etMarca.setText(ropa.marca)
                    etModelo.setText(ropa.modelo)
                    etCosto.setText(ropa.costo.toString())

                    when (ropa.talla) {
                        "Chica" -> rgTalla.check(R.id.rbChica)
                        "Mediana" -> rgTalla.check(R.id.rbMediana)
                        "Grande" -> rgTalla.check(R.id.rbGrande)
                    }

                    setColoresSeleccionados(cgColores, ropa.colores)
                    posicionActual = i
                    Toast.makeText(this, "Prenda encontrada", Toast.LENGTH_SHORT).show()
                    return
                }
            }
        }
        Toast.makeText(this, "Prenda no encontrada", Toast.LENGTH_SHORT).show()
    }

    private fun eliminarRopa(codigo: String) {
        if (posicionActual != -1 && inventario[posicionActual]?.codigo == codigo) {
            inventario[posicionActual] = null
            reordenarArreglo(posicionActual)
            Toast.makeText(this, "Prenda eliminada", Toast.LENGTH_SHORT).show()
            posicionActual = -1
        } else {
            Toast.makeText(this, "No se ha buscado la prenda o código no coincide", Toast.LENGTH_SHORT).show()
        }
    }

    private fun reordenarArreglo(index: Int) {
        for (i in index until inventario.size - 1) {
            inventario[i] = inventario[i + 1]
        }
        inventario[inventario.size - 1] = null
    }

    private fun limpiarCampos(etCodigo: EditText, etMarca: EditText, etModelo: EditText, etCosto: EditText, rgTalla: RadioGroup, cgColores: ChipGroup) {
        etCodigo.text.clear()
        etMarca.text.clear()
        etModelo.text.clear()
        etCosto.text.clear()
        rgTalla.clearCheck()
        for (i in 0 until cgColores.childCount) {
            val chip = cgColores.getChildAt(i) as Chip
            chip.isChecked = false
        }
    }

    private fun setColoresSeleccionados(cgColores: ChipGroup, colores: Set<String>) {
        for (i in 0 until cgColores.childCount) {
            val chip = cgColores.getChildAt(i) as Chip
            chip.isChecked = chip.text in colores
        }
    }
}