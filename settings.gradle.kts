pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
}
include(Modules.Model)
include(Modules.Shared)
include(Modules.Backend)
include(Modules.Android)
include(Modules.Js)
rootProject.name = "KaMP"
