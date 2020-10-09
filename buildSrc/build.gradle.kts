plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    implementation("com.android.tools.build:gradle:4.2.0-alpha12")
    implementation(kotlin("gradle-plugin", "1.4.10"))
    implementation(kotlin("android-extensions"))
}