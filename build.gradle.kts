buildscript {
    val kotlin_version = "1.3.71"
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath(kotlin("gradle-plugin", version = kotlin_version))
    }
}

repositories {
    mavenCentral()
}