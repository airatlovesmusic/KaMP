import org.gradle.api.NamedDomainObjectCollection
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun NamedDomainObjectCollection<KotlinSourceSet>.applyUseExperimentalAnnotations() {
    all {
        languageSettings.apply {
            useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
            useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            useExperimentalAnnotation("kotlinx.coroutines.FlowPreview")
            useExperimentalAnnotation("kotlinx.coroutines.InternalCoroutinesApi")
        }
    }
}