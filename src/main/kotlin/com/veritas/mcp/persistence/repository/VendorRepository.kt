package com.veritas.mcp.persistence.repository

import com.veritas.mcp.persistence.entity.Vendor
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class VendorRepository(
    private val entityManager: EntityManager,
) {
    fun findById(id: Long): Vendor? = entityManager.find(Vendor::class.java, id)

    fun findByName(name: String): Vendor? =
        entityManager.createQuery(
            "SELECT v FROM Vendor v WHERE v.name = :name",
            Vendor::class.java,
        )
            .setParameter("name", name)
            .resultList
            .firstOrNull()
}
