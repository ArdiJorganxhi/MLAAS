package dev.ardijorganxhi.mlaas.service

import dev.ardijorganxhi.mlaas.exception.ApiException
import dev.ardijorganxhi.mlaas.mapper.convertToDto
import dev.ardijorganxhi.mlaas.model.dto.UserDto
import dev.ardijorganxhi.mlaas.model.error.ErrorEnum
import dev.ardijorganxhi.mlaas.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserService(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByEmail(username) ?: throw EntityNotFoundException("Username not found!")
    }

    fun findById(id: Int): UserDto {
        val user = userRepository.findById(id).orElseThrow() ?: throw ApiException(ErrorEnum.USER_NOT_CREATED)
        return user.convertToDto()
    }

    fun deleteById(id: Int) {
        val user = userRepository.findById(id).orElseThrow() ?: throw ApiException(ErrorEnum.USER_NOT_CREATED)
        user.isDeleted = true
        userRepository.save(user)
    }




}