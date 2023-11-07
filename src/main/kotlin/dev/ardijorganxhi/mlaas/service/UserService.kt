package dev.ardijorganxhi.mlaas.service

import dev.ardijorganxhi.mlaas.model.dto.UserDto
import dev.ardijorganxhi.mlaas.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserService(private val userRepository: UserRepository): UserDetailsService {


    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByEmail(username) ?: throw EntityNotFoundException("Username not found!")
    }
}