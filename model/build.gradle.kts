plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
}

repositories {
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    jvm()
    ios {
        binaries {
            framework {
                baseName = "model"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.20.0")
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