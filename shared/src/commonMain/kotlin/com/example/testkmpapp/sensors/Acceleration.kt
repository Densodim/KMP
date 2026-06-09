package com.example.testkmpapp.sensors

// Common model: plain Kotlin data can live in commonMain because Android and iOS both understand it.
data class Acceleration(
    val x: Double,
    val y: Double,
    val z: Double,
)
