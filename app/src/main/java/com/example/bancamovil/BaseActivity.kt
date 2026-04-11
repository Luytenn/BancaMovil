package com.example.bancamovil

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.bancamovil.data.SessionManager
import com.example.bancamovil.domain.use_case.session.GetSessionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity : ComponentActivity() {
    lateinit var sessionManager: SessionManager
    @Inject
    lateinit var sessionUseCase: GetSessionUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val res  = sessionUseCase.invoke("idUserKey")
            res.let {
                if (sessionManager.isSessionExpired()) {
                    onSessionExpired()
                } else {
                    sessionManager.updateLastActivity()
                }
            }
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