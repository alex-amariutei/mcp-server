package com.veritas.mcp.persistence.repository

import com.veritas.mcp.persistence.entity.Tag
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class TagRepository(
    private val entityManager: EntityManager,
) {
    fun findById(id: Long): Tag? = entityManager.find(Tag::class.java, id)
}
