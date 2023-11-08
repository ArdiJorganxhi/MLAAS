package dev.ardijorganxhi.mlaas.controller

import dev.ardijorganxhi.mlaas.model.dto.UserDto
import dev.ardijorganxhi.mlaas.service.UserService
import dev.ardijorganxhi.mlaas.utils.IdentityUserUtils
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getProfile(): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.findById(IdentityUserUtils.getId()))
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.findById(id))
    }
    @DeleteMapping
    fun deleteProfile() {
        userService.deleteById(IdentityUserUtils.getId())
    }
    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Int) {
        userService.deleteById(id)
    }
}