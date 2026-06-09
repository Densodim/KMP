package com.example.testkmpapp.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

// Android implementation: this file must be in androidMain because SensorManager is Android-only.
class AndroidAccelerometer(
    context: Context,
) : Accelerometer, SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private var onReading: ((Acceleration) -> Unit)? = null

    override fun start(
        onReading: (Acceleration) -> Unit,
        onUnavailable: () -> Unit,
    ) {
        val accelerometer = sensor
        if (accelerometer == null) {
            onUnavailable()
            return
        }

        this.onReading = onReading
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun stop() {
        sensorManager.unregisterListener(this)
        onReading = null
    }

    override fun onSensorChanged(event: SensorEvent) {
        onReading?.invoke(
            Acceleration(
                x = event.values[0].toDouble(),
                y = event.values[1].toDouble(),
                z = event.values[2].toDouble(),
            )
        )
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
}
