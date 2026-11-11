package com.veritas.mcp.tools

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

class GreetToolTest {

    private val greetTool = GreetTool()

    @BeforeEach
    fun clearSecurityContext() {
        SecurityContextHolder.clearContext()
    }

    @AfterEach
    fun tearDown() {
        SecurityContextHolder.clearContext()
    }

    @Test
    fun `greets anonymous user in English by default`() {
        assertEquals("Hello, anonymous!", greetTool.greet())
    }

    @Test
    fun `greets anonymous user in English when language is null`() {
        assertEquals("Hello, anonymous!", greetTool.greet(language = null))
    }

    @Test
    fun `greets anonymous user in French`() {
        assertEquals("Bonjour, anonymous!", greetTool.greet(language = "french"))
    }

    @Test
    fun `french language is case insensitive`() {
        assertEquals("Bonjour, anonymous!", greetTool.greet(language = "French"))
        assertEquals("Bonjour, anonymous!", greetTool.greet(language = "FRENCH"))
    }

    @Test
    fun `unknown language falls back to English`() {
        assertEquals("Hello, anonymous!", greetTool.greet(language = "spanish"))
        assertEquals("Hello, anonymous!", greetTool.greet(language = "english"))
    }

    @Test
    fun `greets authenticated user in English`() {
        setAuthenticatedUser("alice")

        assertEquals("Hello, alice!", greetTool.greet())
    }

    @Test
    fun `greets authenticated user in French`() {
        setAuthenticatedUser("alice")

        assertEquals("Bonjour, alice!", greetTool.greet(language = "french"))
    }

    private fun setAuthenticatedUser(name: String) {
        val authentication = UsernamePasswordAuthenticationToken(name, "n/a", emptyList())
        SecurityContextHolder.getContext().authentication = authentication
    }
}
