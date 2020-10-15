import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://dl.bintray.com/touchlabpublic/kotlin") // TODO remove this once Koin is officially published
    maven(url = "https://dl.bintray.com/badoo/maven")
}

kotlin {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
        JavaVersion.VERSION_1_8.toString().also {
            kotlinOptions.jvmTarget = it
            if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                sourceCompatibility = it
                targetCompatibility = it
            }
        }
    }
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    js {
        browser()
    }

    sourceSets {
        applyUseExperimentalAnnotations()
        val commonMain by getting {
            dependencies {
                implementation (Dependencies.Common.Serialization)
                implementation(Dependencies.ktorClient.common)
                implementation(Dependencies.Common.Reaktive.Core)
                implementation(Dependencies.Common.Reaktive.Utils)
                implementation(Dependencies.Common.Reaktive.CoroutinesInterop)
                implementation(Dependencies.Common.Ktor.Json)
                implementation(Dependencies.Common.Ktor.Serialization)
                implementation(Dependencies.Common.Ktor.Logging)
                implementation(Dependencies.Common.Logback)
                api(Dependencies.Common.Koin)
                api(project(Modules.Model))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Android.AndroidX.AppCompat)
                implementation(Dependencies.Android.AndroidX.CoreKtx)
                implementation(Dependencies.ktorClient.android)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Dependencies.ktorClient.iOS)
                implementation(Dependencies.iOS.Coroutines) { isForce = true }
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(Dependencies.ktorClient.js)
            }
        }
    }
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)