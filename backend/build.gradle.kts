plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.heroku.sdk.heroku-gradle") version "1.0.4"
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

heroku {
    appName = "kmp-backend"
    includes = listOf("./backend/build/libs/backend.jar")
    jdkVersion = "8"
}

dependencies {
    implementation(project(path =":model"))
    implementation(kotlin("stdlib"))

    val ktorVersion = "1.4.0"
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation ("io.ktor:ktor-server-netty:$ktorVersion")
    implementation ("io.ktor:ktor-gson:$ktorVersion")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    create("stage") {
        dependsOn(getByName("installDist"))
    }
}