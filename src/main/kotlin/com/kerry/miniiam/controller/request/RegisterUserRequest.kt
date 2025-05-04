package com.kerry.miniiam.controller.request

data class RegisterUserRequest(
    val username: String,
    val password: String,
    val roles: Set<String> = setOf("ROLE_USER")
)