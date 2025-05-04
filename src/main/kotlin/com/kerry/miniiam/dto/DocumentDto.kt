package com.kerry.miniiam.dto

import com.kerry.miniiam.entity.Document

data class DocumentDto(
    val id: Long,
    val title: String,
    val content: String
) {
    companion object {
        fun from(doc: Document): DocumentDto =
            DocumentDto(doc.id!!, doc.title, doc.content)
    }
}