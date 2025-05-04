package com.kerry.miniiam.controller

import com.kerry.miniiam.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class LoginViewController(
    private val userService: UserService
) {

    @GetMapping("/login")
    fun loginPage(): String {
        return "login"
    }

    @GetMapping("/register")
    fun registerForm(): String = "register"

    @PostMapping("/register")
    fun register(
        @RequestParam username: String,
        @RequestParam password: String,
        redirectAttributes: RedirectAttributes
    ): String {
        return try {
            userService.createUser(username, password, setOf("ROLE_USER"))
            redirectAttributes.addAttribute("success", "")
            "redirect:/register"
        } catch (e: Exception) {
            redirectAttributes.addAttribute("error", "")
            "redirect:/register"
        }
    }
}