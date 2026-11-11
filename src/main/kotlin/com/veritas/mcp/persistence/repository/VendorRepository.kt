package com.veritas.mcp.persistence.repository

import com.veritas.mcp.persistence.entity.Vendor
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class VendorRepository(
    private val entityManager: EntityManager,
) {
    fun findById(id: Long): Vendor? = entityManager.find(Vendor::class.java, id)
}
