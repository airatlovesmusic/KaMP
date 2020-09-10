pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
}
include(":model")
include (":shared")
include (":backend")
include (":android:app")
rootProject.name = "KaMP"

enableFeaturePreview("GRADLE_METADATA")
