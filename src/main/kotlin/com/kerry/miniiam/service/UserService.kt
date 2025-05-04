package com.kerry.miniiam.service

import com.kerry.miniiam.entity.User
import com.kerry.miniiam.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
){
    fun createUser(username: String, rawPassword: String, roles: Set<String> = setOf("ROLE_USER")): User {
        val encoded = passwordEncoder.encode(rawPassword)
        if (userRepository.findByUsername(username) != null) {
            throw IllegalArgumentException("Username already exists")
        }
        return userRepository.save(User.create(username = username, password = encoded, roles = roles))
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}