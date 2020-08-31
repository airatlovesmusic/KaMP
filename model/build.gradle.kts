plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

repositories {
    mavenCentral()
}

publishing {
    repositories {
        mavenLocal()
    }
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting
    }
}