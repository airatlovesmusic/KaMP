import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    kotlin("jvm")
    application
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}


kotlin {
    experimental {
        coroutines = Coroutines.ENABLE
    }
}

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/ktor")
}

dependencies {
    implementation(project(":model"))
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
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
