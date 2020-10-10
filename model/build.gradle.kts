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
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
                useExperimentalAnnotation("kotlinx.coroutines.FlowPreview")
                useExperimentalAnnotation("kotlinx.coroutines.InternalCoroutinesApi")
            }
        }
    }
}