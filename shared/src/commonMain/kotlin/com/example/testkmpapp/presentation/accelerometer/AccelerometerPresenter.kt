package com.example.testkmpapp.presentation.accelerometer

import com.example.testkmpapp.sensors.Accelerometer

// Common logic: this file belongs in commonMain because it has no Android or iOS imports.
// The platform-specific Accelerometer is injected through the interface.
class AccelerometerPresenter(
    private val accelerometer: Accelerometer,
) {
    private var view: AccelerometerView? = null

    fun attach(view: AccelerometerView) {
        this.view = view
    }

    fun detach() {
        stop()
        view = null
    }

    fun start() {
        accelerometer.start(
            onReading = { acceleration -> view?.showAcceleration(acceleration) },
            onUnavailable = { view?.showAccelerometerUnavailable() },
        )
    }

    fun stop() {
        accelerometer.stop()
    }
}
