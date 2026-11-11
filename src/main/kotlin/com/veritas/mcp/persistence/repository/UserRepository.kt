package com.veritas.mcp.persistence.repository

import com.veritas.mcp.persistence.entity.User
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val entityManager: EntityManager,
) {
    fun findById(id: Long): User? = entityManager.find(User::class.java, id)

    fun findByFirstNameAndLastName(firstName: String, lastName: String): User? =
        entityManager.createQuery(
            "SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName",
            User::class.java,
        )
            .setParameter("firstName", firstName)
            .setParameter("lastName", lastName)
            .resultList
            .firstOrNull()
}
