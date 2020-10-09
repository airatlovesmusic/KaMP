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

}