plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
}

application {
    mainClassName = "com.airatlovesmusic.backend.MainKt"
}

dependencies {
    implementation(project(path =":model"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")

    val ktorVersion = "1.4.0"
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation ("io.ktor:ktor-server-netty:$ktorVersion")
    implementation ("io.ktor:ktor-gson:$ktorVersion")
    implementation ("io.ktor:ktor-auth:$ktorVersion")
    // database
    implementation("org.jetbrains.exposed:exposed:0.17.7")
    implementation ("org.jetbrains.exposed:exposed-jdbc:0.17.7")
    implementation ("org.jetbrains.exposed:exposed-dao:0.17.7")

    implementation ("com.h2database:h2:1.4.200")
    implementation ("com.zaxxer:HikariCP:3.4.2")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    create("stage") {
        dependsOn(getByName("installDist"))
    }
}