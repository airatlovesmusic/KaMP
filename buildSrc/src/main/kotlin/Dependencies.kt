data class MultiplatformDependency(
    val android: String,
    val common: String,
    val iOS: String,
    val js: String
)

object Dependencies {

    val ktorClient = MultiplatformDependency(
        android = "io.ktor:ktor-client-okhttp:${Versions.ktorVersion}",
        common = "io.ktor:ktor-client-core:${Versions.ktorVersion}",
        iOS = "io.ktor:ktor-client-ios:${Versions.ktorVersion}",
        js = "io.ktor:ktor-client-js:${Versions.ktorVersion}"
    )

    object Common {

        object Reaktive {
            const val Core = "com.badoo.reaktive:reaktive:1.1.17"
            const val Utils = "com.badoo.reaktive:utils:1.1.17"
            const val CoroutinesInterop = "com.badoo.reaktive:coroutines-interop:1.1.17"
        }

        object Ktor {
            const val Json = "io.ktor:ktor-client-json:${Versions.ktorVersion}"
            const val Serialization = "io.ktor:ktor-client-serialization:${Versions.ktorVersion}"
            const val Logging = "io.ktor:ktor-client-logging:${Versions.ktorVersion}"
        }

        const val Logback = "ch.qos.logback:logback-classic:${Versions.Logback}"
        const val Koin = "org.koin:koin-core:${Versions.Koin}"
        const val Serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Serialization}"

    }

    object Android {
        object AndroidX {
            val CoreKtx = "androidx.core:core-ktx:1.3.2"
            val AppCompat = "androidx.appcompat:appcompat:1.2.0"
        }
    }

    object iOS {
        val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9-native-mt"
    }

}