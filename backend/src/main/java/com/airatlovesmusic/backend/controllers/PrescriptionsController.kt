package com.airatlovesmusic.backend.controllers

import com.airatlovesmusic.backend.data.repository.PrescriptionsRepository
import com.airatlovesmusic.backend.entity.User
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.prescriptions() {
    val prescriptionsRepository = PrescriptionsRepository()
    get("prescriptions") {
        val principal = call.principal<User>()
        principal?.id?.let {
            val prescriptions = prescriptionsRepository.getPrescriptionsByUserId(principal.id)
            call.respond(prescriptions)
        } ?: call.respond(HttpStatusCode.Unauthorized)
    }
}