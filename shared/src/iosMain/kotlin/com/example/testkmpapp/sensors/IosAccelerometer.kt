package com.example.testkmpapp.sensors

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreMotion.CMMotionManager
import platform.Foundation.NSOperationQueue

@OptIn(ExperimentalForeignApi::class)
class IosAccelerometer : Accelerometer {
    private val motionManager = CMMotionManager()

    override fun start(
        onReading: (Acceleration) -> Unit,
        onUnavailable: () -> Unit,
    ) {
        if (!motionManager.accelerometerAvailable) {
            onUnavailable()
            return
        }

        motionManager.accelerometerUpdateInterval = 0.2
        motionManager.startAccelerometerUpdatesToQueue(NSOperationQueue.mainQueue) { data, _ ->
            val acceleration = data?.acceleration ?: return@startAccelerometerUpdatesToQueue
            val reading = acceleration.useContents {
                Acceleration(x = x, y = y, z = z)
            }
            onReading(reading)
        }
    }

    override fun stop() {
        motionManager.stopAccelerometerUpdates()
    }
}
