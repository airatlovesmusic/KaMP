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

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
                useExperimentalAnnotation("kotlinx.coroutines.FlowPreview")
                useExperimentalAnnotation("kotlinx.coroutines.InternalCoroutinesApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")
                implementation("com.badoo.reaktive:reaktive:1.1.17")
                implementation("com.badoo.reaktive:utils:1.1.17")
                api("org.koin:koin-core:3.0.1-alpha-2")
                api(project(":model"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation("androidx.core:core-ktx:1.3.1")
                implementation("androidx.appcompat:appcompat:1.2.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
        }
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
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