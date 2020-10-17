import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(Android.compileSdkVerion)
    defaultConfig {
        applicationId("com.airatlovesmusic.kamp")
        targetSdkVersion(Android.targetSdkVerion)
        minSdkVersion(Android.minSdkVerion)
        versionCode = 1
        versionName = "1.0"
    }

    android.buildFeatures.viewBinding = true

    buildTypes {
        findByName("release")?.apply {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType(KotlinCompile::class.java).all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://dl.bintray.com/touchlabpublic/kotlin") // TODO remove this once Koin is officially published
    maven(url = "https://dl.bintray.com/badoo/maven")
}

dependencies {
    implementation(project(Modules.Shared))
    implementation(Dependencies.Android.AndroidX.AppCompat)
    implementation(Dependencies.Android.AndroidX.CoreKtx)
    implementation("com.google.android.material:material:1.3.0-alpha03")
}