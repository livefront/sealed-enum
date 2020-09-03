import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    const val autoServiceAnnotations = "com.google.auto.service:auto-service-annotations:${Versions.autoService}"
    const val autoService = "com.google.auto.service:auto-service:${Versions.autoService}"
    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
    const val incap = "net.ltgt.gradle.incap:incap:${Versions.incap}"
    const val incapProcessor = "net.ltgt.gradle.incap:incap-processor:${Versions.incap}"
    const val junit = "org.junit.jupiter:junit-jupiter:${Versions.junit}"
    const val kotlinPoet = "com.squareup:kotlinpoet:${Versions.kotlinPoet}"
    const val kotlinPoetClassInspectorElements = "com.squareup:kotlinpoet-classinspector-elements:${Versions.kotlinPoet}"
    const val kotlinPoetMetadata = "com.squareup:kotlinpoet-metadata:${Versions.kotlinPoet}"
    const val kotlinPoetMetadataSpecs = "com.squareup:kotlinpoet-metadata-specs:${Versions.kotlinPoet}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
}

fun DependencyHandler.incap() {
    implementation(Dependencies.incap)
    kapt(Dependencies.incapProcessor)
}

fun DependencyHandler.autoService() {
    implementation(Dependencies.autoServiceAnnotations)
    kapt(Dependencies.autoService)
}

fun DependencyHandler.implementation(dependencyName: String) {
    add("implementation", dependencyName)
}

fun DependencyHandler.kapt(dependencyName: String) {
    add("kapt", dependencyName)
}

fun DependencyHandler.detektPlugins(dependencyName: String) {
    add("detektPlugins", dependencyName)
}

fun DependencyHandler.testImplementation(dependencyName: String) {
    add("testImplementation", dependencyName)
}
