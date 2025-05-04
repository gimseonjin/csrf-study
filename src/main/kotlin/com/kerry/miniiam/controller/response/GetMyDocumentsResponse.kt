package com.kerry.miniiam.controller.response

import com.kerry.miniiam.dto.DocumentDto
import com.kerry.miniiam.entity.Document

data class GetMyDocumentsResponse (
    val documents: List<DocumentDto>
) {
    companion object {
        fun from(documents: List<Document>): GetMyDocumentsResponse {
            return GetMyDocumentsResponse(
                documents = documents.map {
                    DocumentDto(
                        id = it.id!!,
                        title = it.title,
                        content = it.content,
                    )
                }
            )
        }
    }
}