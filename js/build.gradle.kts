plugins {
    kotlin("js")
}

repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    maven(url = "https://dl.bintray.com/touchlabpublic/kotlin") // TODO remove this once Koin is officially published
    maven(url = "https://dl.bintray.com/badoo/maven")
    mavenCentral()
    jcenter()
}

kotlin {
    js {
        browser {
//            compilations.all {
//                kotlinOptions {
//                    metaInfo = true
//                    sourceMap = true
//                    main = "call"
//                }
//            }
        }
        binaries.executable()
    }
}

dependencies {
//    implementation(project(":shared"))
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1")

    implementation("org.jetbrains:kotlin-react:16.13.0-pre.94-kotlin-1.3.70")
    implementation("org.jetbrains:kotlin-react-dom:16.13.0-pre.94-kotlin-1.3.70")
    implementation("org.jetbrains:kotlin-react-router-dom:4.3.1-pre.94-kotlin-1.3.70")
    implementation("org.jetbrains:kotlin-extensions:1.0.1-pre.94-kotlin-1.3.70")

    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))
    implementation(npm("react-router-dom", "4.3.1"))
}