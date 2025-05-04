package com.kerry.miniiam.controller

import com.kerry.miniiam.config.auth.UserPrincipal
import com.kerry.miniiam.controller.request.CreateDocRequest
import com.kerry.miniiam.dto.DocumentDto
import com.kerry.miniiam.service.DocumentService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/documents")
class DocumentController(
    private val documentService: DocumentService
) {

    @PostMapping
    fun create(
        @RequestBody req: CreateDocRequest,
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<Long> {
        val doc = documentService.createDocument(req.title, req.content, user.getDomainUser())
        return ResponseEntity.ok(doc.id!!)
    }

    @GetMapping
    fun list(
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<List<DocumentDto>> {
        val docs = documentService.getMyDocuments(user.getDomainUser())
            .map { DocumentDto.from(it) }
        return ResponseEntity.ok(docs)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody req: CreateDocRequest,
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<Unit> {
        documentService.updateDocument(id, req.title, req.content, user.getDomainUser())
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<Unit> {
        documentService.deleteDocument(id, user.getDomainUser())
        return ResponseEntity.noContent().build()
    }
}
