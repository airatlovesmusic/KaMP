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
include(":js")
rootProject.name = "KaMP"
