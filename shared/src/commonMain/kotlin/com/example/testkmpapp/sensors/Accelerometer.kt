package com.example.testkmpapp.sensors

// Common contract: the presenter depends on this interface, not on Android SensorManager or iOS CoreMotion.
// Platform source sets provide implementations that know the native APIs.
interface Accelerometer {
    fun start(
        onReading: (Acceleration) -> Unit,
        onUnavailable: () -> Unit = {},
    )

    fun stop()
}
