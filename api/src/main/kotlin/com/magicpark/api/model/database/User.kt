package com.magicpark.api.model.database

import com.fasterxml.jackson.annotation.JsonAlias
import com.magicpark.api.enums.UserRank
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Date
import java.sql.Timestamp
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "magicpark_users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,

    @Column(name = "fullname")
    @JsonAlias("fullname")
    var fullName: String? = null,


    @Column(name = "mail")
    var mail: String? = null,

    @NotNull
    @Column(name = "country")
    var country: String? = null,

    @NotNull
    @Column(name = "rank")
    var rank: UserRank = UserRank.CUSTOMER,

    @NotNull
    @Column(name = "token")
    var token: String? = null,

    @NotNull
    @Column(name = "ip")
    var ip: String? = null,

    @Column(name = "phone_number")
    @JsonAlias("phone_number")
    var phoneNumber: String? = null,

    @Column(name = "created_at")
    @JsonAlias("created_at")
    var createdAt: Date? = null,

    @Column(name = "updated_at")
    @JsonAlias("updated_at")
    var updatedAt: Date? = null,

    @Column(name = "deleted_at")
    @JsonAlias("deleted_at")
    var deletedAt: Date? = null,
)