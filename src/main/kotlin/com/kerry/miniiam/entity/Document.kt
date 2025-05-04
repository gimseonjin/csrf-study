package com.kerry.miniiam.entity

import jakarta.persistence.*

@Entity
@Table(name = "documents")
class Document(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var title: String,
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var owner: User
) {
    companion object {
        fun create(title: String, content: String, owner: User): Document {
            return Document(null, title, content, owner)
        }
    }

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}