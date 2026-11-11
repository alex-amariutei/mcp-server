package com.veritas.mcp.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var auth0UserId: String = "",

    @Column(nullable = false)
    var firstName: String = "",

    @Column(nullable = false)
    var lastName: String = "",

    @Column(nullable = false)
    var hasDirectReports: Boolean = false,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_transactions",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "transaction_id")],
    )
    var transactions: MutableSet<Transaction> = mutableSetOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_connections",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "connected_user_id")],
    )
    var connections: MutableSet<User> = mutableSetOf(),
)
