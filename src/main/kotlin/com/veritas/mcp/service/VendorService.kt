package com.veritas.mcp.service

import com.veritas.mcp.persistence.entity.Vendor
import com.veritas.mcp.persistence.repository.VendorRepository
import org.springframework.stereotype.Service

@Service
class VendorService(
    private val vendorRepository: VendorRepository,
) {
    fun get(id: Long): Vendor? = vendorRepository.findById(id)

    fun getByName(name: String): Vendor? = vendorRepository.findByName(name)
}
