package com.example.testkmpapp.presentation.accelerometer

import com.example.testkmpapp.sensors.Acceleration

// Common presentation contract: native Android/iOS screens implement View, shared presenter drives behavior.
interface AccelerometerView {
    val presenter: AccelerometerPresenter

    fun showAcceleration(acceleration: Acceleration)

    fun showAccelerometerUnavailable()
}
