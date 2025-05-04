package com.kerry.miniiam.controller

import com.kerry.miniiam.config.auth.UserPrincipal
import com.kerry.miniiam.controller.request.RegisterUserRequest
import com.kerry.miniiam.dto.UserInfoDto
import com.kerry.miniiam.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun register(
        @RequestBody req: RegisterUserRequest
    ): ResponseEntity<String> {
        userService.createUser(req.username, req.password, req.roles)
        return ResponseEntity.ok("User created")
    }

    @GetMapping("/me")
    fun me(
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<UserInfoDto> {
        val domainUser = user.getDomainUser()
        return ResponseEntity.ok(UserInfoDto(domainUser.username, domainUser.roles))
    }
}
