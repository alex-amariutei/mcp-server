package com.veritas.mcp.persistence.repository

import com.veritas.mcp.persistence.entity.Transaction
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class TransactionRepository(
    private val entityManager: EntityManager,
) {
    fun findById(id: Long): Transaction? = entityManager.find(Transaction::class.java, id)
}
