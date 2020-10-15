object Dependencies {

    data class MultiplatformDependency(
        val android: String,
        val common: String,
        val iOS: String,
        val js: String
    )

    val ktorClient = MultiplatformDependency(
        android = "io.ktor:ktor-client-okhttp:${Versions.Ktor}",
        common = "io.ktor:ktor-client-core:${Versions.Ktor}",
        iOS = "io.ktor:ktor-client-ios:${Versions.Ktor}",
        js = "io.ktor:ktor-client-js:${Versions.Ktor}"
    )

    object Common {

        object Reaktive {
            const val Core = "com.badoo.reaktive:reaktive:1.1.17"
            const val Utils = "com.badoo.reaktive:utils:1.1.17"
            const val CoroutinesInterop = "com.badoo.reaktive:coroutines-interop:1.1.17"
        }

        object Ktor {
            const val Json = "io.ktor:ktor-client-json:${Versions.Ktor}"
            const val Serialization = "io.ktor:ktor-client-serialization:${Versions.Ktor}"
            const val Logging = "io.ktor:ktor-client-logging:${Versions.Ktor}"
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
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9-native-mt"
    }

    object Backend {
        const val SerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Serialization}"
        const val Logback = "ch.qos.logback:logback-classic:${Versions.Logback}"

        object Ktor {
            const val Netty = "io.ktor:ktor-server-netty:${Versions.KtorBackend}"
            const val Auth = "io.ktor:ktor-auth:${Versions.KtorBackend}"
            const val AuthJwt = "io.ktor:ktor-auth-jwt:${Versions.KtorBackend}"
            const val Gson = "io.ktor:ktor-gson:${Versions.KtorBackend}"
        }

        const val JBCrypt = "org.mindrot:jbcrypt:0.4"

        object Database {
            const val Exposed = "org.jetbrains.exposed:exposed:0.17.7"
            const val H2 = "com.h2database:h2:1.4.200"
            const val Hikari = "com.zaxxer:HikariCP:3.4.2"
        }

    }

}