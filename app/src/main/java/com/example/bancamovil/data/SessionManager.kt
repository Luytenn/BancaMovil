package com.example.bancamovil.data


class SessionManager(
) {
    private var lastInteractionTime: Long? = null
    private val timeout = 2 * 60 * 1000L


    fun updateLastActivity() {
        lastInteractionTime = System.currentTimeMillis()
    }

    fun isSessionExpired(): Boolean {
        val lastTime = lastInteractionTime ?: return false
        return System.currentTimeMillis() - lastTime > timeout
    }

    fun clearSession() {
        lastInteractionTime = null
    }

    fun isSessionActive(): Boolean {
        return lastInteractionTime != null
    }
}