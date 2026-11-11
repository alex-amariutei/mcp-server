package com.veritas.mcp.service

import com.veritas.mcp.persistence.entity.Transaction
import com.veritas.mcp.persistence.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
) {
    fun get(id: Long): Transaction? = transactionRepository.findById(id)
}
