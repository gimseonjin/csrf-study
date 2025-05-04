package com.kerry.miniiam.service

import com.kerry.miniiam.entity.Document
import com.kerry.miniiam.entity.User
import com.kerry.miniiam.repository.DocumentRepository
import org.springframework.stereotype.Service

@Service
class DocumentService(
    private val documentRepository: DocumentRepository,
) {
    fun createDocument(title: String, content: String, owner: User): Document {
        return documentRepository.save(Document.create(title = title, content = content, owner = owner))
    }

    fun getMyDocuments(user: User): List<Document> {
        return documentRepository.findAllByOwnerUsername(user.username)
    }

    fun updateDocument(id: Long, title: String, content: String, user: User) {
        val doc = documentRepository.findById(id).orElseThrow()
        check(doc.owner.equals(user)) { "권한 없음" }

        doc.update(title = title, content = content)

        documentRepository.save(doc)
    }

    fun deleteDocument(id: Long, user: User) {
        val doc = documentRepository.findById(id).orElseThrow()
        check(doc.owner.equals(user)) { "권한 없음" }

        documentRepository.delete(doc)
    }
}