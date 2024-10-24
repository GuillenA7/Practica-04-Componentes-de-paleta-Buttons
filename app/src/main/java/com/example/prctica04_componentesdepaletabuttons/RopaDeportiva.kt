package com.example.prctica04_componentesdepaletabuttons

// RopaDeportiva.kt
data class RopaDeportiva(
    var codigo: String,
    var marca: String,
    var modelo: String,
    var talla: String, // chica, mediana, grande
    var colores: Set<String>, // negro, blanco, azul, rojo, naranja
    var costo: Double
)