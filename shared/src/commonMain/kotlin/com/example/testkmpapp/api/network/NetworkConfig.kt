package com.example.testkmpapp.api.network

class NetworkConfig {
    val header: Map<String, String> = mapOf(
        "X-Api-Key" to NetworkConfiguration.API_KEY,
        "Content-Type" to "application/json"
    )
}
