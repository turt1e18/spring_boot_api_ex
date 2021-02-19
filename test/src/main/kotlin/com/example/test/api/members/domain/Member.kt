package com.example.test.api.members.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class Member(
        @Id
        @Column(name = "id")
        var id: String = "",

        @Column(name = "name")
        var name: String = "",
        @Column(name = "type")
        var type: String = "",
        @Column(name = "password")
        var password: String = "",
        @Column(name = "email")
        var email: String = ""
)
