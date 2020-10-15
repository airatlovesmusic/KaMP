plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    jvm()
    ios()
    js { browser() }
    sourceSets {
        commonMain {
            dependencies {
                implementation (Dependencies.Common.Serialization)
            }
        }
        applyUseExperimentalAnnotations()
    }
}