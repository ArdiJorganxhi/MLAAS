package dev.ardijorganxhi.mlaas.repository

import dev.ardijorganxhi.mlaas.entity.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Int> {

    fun findByEmail(email: String?) : User?
}