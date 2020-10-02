package com.airatlovesmusic.backend.controllers

import com.airatlovesmusic.backend.data.repository.PrescriptionsRepository
import com.airatlovesmusic.backend.entity.CreatePrescription
import com.airatlovesmusic.backend.entity.User
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.prescriptions() {
    val prescriptionsRepository = PrescriptionsRepository()
    route("prescriptions") {
        get {
            val user = call.principal<User>()
            user?.id?.let {
                val prescriptions = prescriptionsRepository.getPrescriptionsByUserId(user.id)
                call.respond(prescriptions)
            } ?: call.respond(HttpStatusCode.Unauthorized)
        }
        post {
            val request = call.receive<CreatePrescription>()
            val user = call.principal<User>()
            user?.id?.let {
                val newPrescription = prescriptionsRepository.createPrescription(request)
                if (newPrescription == null) call.respond(HttpStatusCode.InternalServerError)
                else call.respond(newPrescription)
            } ?: call.respond(HttpStatusCode.Unauthorized)
        }
    }
}