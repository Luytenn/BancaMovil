package com.example.bancamovil.util

object InputFilter {
    private val user_regex = Regex("^[a-zA-Z0-9@._-]{6,30}\$")

    fun filterUsername(username: String): String? {
        return username.filter { it.toString().matches(user_regex) }
    }



}