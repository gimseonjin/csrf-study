package com.kerry.miniiam.repository

import com.kerry.miniiam.entity.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository: JpaRepository<Document, Long> {
    fun findAllByOwnerUsername(username: String): List<Document>
}