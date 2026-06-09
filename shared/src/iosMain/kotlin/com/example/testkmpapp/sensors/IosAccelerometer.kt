package com.example.testkmpapp.sensors

import platform.CoreMotion.CMMotionManager
import platform.Foundation.NSOperationQueue

// iOS implementation: this file must be in iosMain because CoreMotion exists only on Apple platforms.
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
            onReading(
                Acceleration(
                    x = acceleration.x,
                    y = acceleration.y,
                    z = acceleration.z,
                )
            )
        }
    }

    override fun stop() {
        motionManager.stopAccelerometerUpdates()
    }
}
