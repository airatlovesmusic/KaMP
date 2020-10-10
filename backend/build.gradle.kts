plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
}

application {
    mainClassName = "com.airatlovesmusic.backend.MainKt"
}

dependencies {
    implementation(project(Modules.Model))
    implementation(kotlin("stdlib"))

    implementation(Dependencies.Backend.SerializationJson)
    implementation(Dependencies.Backend.Logback)
    implementation(Dependencies.Backend.JBCrypt)

    implementation(Dependencies.Backend.Ktor.Auth)
    implementation(Dependencies.Backend.Ktor.AuthJwt)
    implementation(Dependencies.Backend.Ktor.Gson)
    implementation(Dependencies.Backend.Ktor.Netty)

    implementation(Dependencies.Backend.Database.Exposed)
    implementation(Dependencies.Backend.Database.H2)
    implementation(Dependencies.Backend.Database.Hikari)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    create("stage") {
        dependsOn(getByName("installDist"))
    }
}