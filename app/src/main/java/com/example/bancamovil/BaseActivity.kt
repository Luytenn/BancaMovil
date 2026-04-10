package com.example.bancamovil

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.bancamovil.data.SessionManager

abstract class BaseActivity : ComponentActivity() {
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager()
    }

    override fun onResume() {
        super.onResume()
        if (sessionManager.isSessionExpired()) {
            onSessionExpired()
        } else {
            sessionManager.updateLastActivity()
        }
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        sessionManager.updateLastActivity()
    }

    open fun onSessionExpired() {
        sessionManager.clearSession()
        // Navegar a Login
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        )
    }
}