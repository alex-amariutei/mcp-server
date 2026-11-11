package com.veritas.mcp.persistence.repository

import com.veritas.mcp.persistence.entity.User
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val entityManager: EntityManager,
) {
    fun findById(id: Long): User? = entityManager.find(User::class.java, id)
}
