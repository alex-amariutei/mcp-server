package com.veritas.mcp.service

import com.veritas.mcp.persistence.entity.Tag
import com.veritas.mcp.persistence.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepository: TagRepository,
) {
    fun get(id: Long): Tag? = tagRepository.findById(id)

    fun getByName(name: String): Tag? = tagRepository.findByName(name)
}
