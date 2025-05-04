package com.kerry.miniiam.entity

import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var username: String,
    var password: String,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "role")
    val roles: Set<String> = setOf()
) {
    companion object {
        fun create(username: String, password: String, roles: Set<String> = setOf("ROLE_USER")): User {
            return User(null, username, password, roles)
        }
    }

    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', roles=$roles)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + username.hashCode()
        return result
    }
}