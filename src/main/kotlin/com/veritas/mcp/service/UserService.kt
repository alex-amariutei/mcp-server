package com.veritas.mcp.service

import com.veritas.mcp.persistence.entity.User
import com.veritas.mcp.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun get(id: Long): User? = userRepository.findById(id)

    fun getByFirstNameAndLastName(firstName: String, lastName: String): User? =
        userRepository.findByFirstNameAndLastName(firstName, lastName)
}
