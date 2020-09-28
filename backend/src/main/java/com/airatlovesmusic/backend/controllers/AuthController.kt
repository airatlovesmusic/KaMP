package com.airatlovesmusic.backend.controllers

import com.airatlovesmusic.backend.data.JWTConfig
import com.airatlovesmusic.backend.data.repository.UsersRepository
import com.airatlovesmusic.backend.entity.User
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.mindrot.jbcrypt.BCrypt

data class LoginRegister(val username: String, val password: String)

fun Routing.auth() {
    val repository = UsersRepository()
    post("/login") {
        val request = call.receive<LoginRegister>()
        val user = repository.getUserByUsername(request.username)
        if (user == null || !BCrypt.checkpw(request.password, user.password)) {
            call.respond(HttpStatusCode.Unauthorized)
        } else call.respond(JWTConfig.makeToken(user))
    }
    post("/register") {
        val request = call.receive<LoginRegister>()
        val user = repository.getUserByUsername(request.username)
        if (user != null) { error("There is already account with this username") }
        else {
            val newUser = repository.createUser(request.username, request.password)
            if (newUser == null) call.respond(HttpStatusCode.InternalServerError)
            else call.respond(JWTConfig.makeToken(newUser))
        }
    }
    authenticate {
        get("/user") {
            val principal = call.principal<User>()
            call.respond(principal ?: "Unauthorized")
        }
    }
}