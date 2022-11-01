package com.magicpark.api.model.database

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "magicpark_user_deletion")
data class UserDeletion(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,

    @Column(name="user_id")
    var userId: Long? = null,
    var token: String? = null,

    @Column(name="expires_at")
    var expiresAt: Timestamp? = null
)