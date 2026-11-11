package com.veritas.mcp.persistence.repository

import com.veritas.mcp.persistence.entity.Transaction
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class TransactionRepository(
    private val entityManager: EntityManager,
) {
    fun findById(id: Long): Transaction? = entityManager.find(Transaction::class.java, id)

    fun findAllByUserId(userId: Long): List<Transaction> =
        entityManager.createQuery(
            "SELECT t FROM User u JOIN u.transactions t WHERE u.auth0UserId = :userId",
            Transaction::class.java,
        )
            .setParameter("userId", userId)
            .resultList
}
