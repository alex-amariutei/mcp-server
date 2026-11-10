package com.veritas.mcp.tools

import org.springframework.ai.mcp.annotation.McpTool
import org.springframework.ai.mcp.annotation.McpToolParam
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class GreetTool {

    @McpTool(
        name = "greet",
        description = "This tool should be used to greet the user. Requires Auth0 authentication to customize the message. ",
    )
    fun greet(
        @McpToolParam(
            description = "Optional language for the greeting (English or French)",
            required = false,
        )
        language: String? = null,
    ): String {
        val name = SecurityContextHolder.getContext().authentication?.name ?: "anonymous"
        return when (language?.lowercase()) {
            "french" -> "Bonjour, $name!"
            else -> "Hello, $name!"
        }
    }
}