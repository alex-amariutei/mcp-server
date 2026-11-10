package com.veritas.mcp

import org.springaicommunity.mcp.security.server.config.McpServerOAuth2Configurer.mcpServerOAuth2
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val issuerUri: String,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .csrf(CsrfConfigurer<HttpSecurity>::disable)
            .cors { it.configurationSource(corsConfigurationSource()) }
            .with(mcpServerOAuth2()) { mcp ->
                mcp.authorizationServer(issuerUri)
                mcp.validateAudienceClaim(true)
                mcp.allowedOrigins(
                    listOf(
                        "http://localhost:*",
                        "http://127.0.0.1:*",
                        "http://[::1]:*",
                    ),
                )
            }
            .build()

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedOriginPatterns = listOf("*")
            allowedMethods = listOf("*")
            allowedHeaders = listOf("*")
            exposedHeaders = listOf("*")
            allowCredentials = true
        }
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
    }
}
