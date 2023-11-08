package dev.ardijorganxhi.mlaas.service

import dev.ardijorganxhi.mlaas.mapper.UserMapper
import dev.ardijorganxhi.mlaas.model.dto.UserDto
import dev.ardijorganxhi.mlaas.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserService(private val userRepository: UserRepository, private val userMapper: UserMapper): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByEmail(username) ?: throw EntityNotFoundException("Username not found!")
    }

    fun findById(id: Int): UserDto {
        val user = userRepository.findById(id).orElseThrow()
        return userMapper.convertToDto(user = user)
    }

    fun deleteById(id: Int) {
        val user = userRepository.findById(id).orElseThrow()
        user.isDeleted = true
        userRepository.save(user)
    }




}