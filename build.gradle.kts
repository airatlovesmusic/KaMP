buildscript {
    val kotlin_version = "1.4.0"
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
        classpath(kotlin("gradle-plugin", version = kotlin_version))
    }
}

repositories {
    mavenCentral()
}