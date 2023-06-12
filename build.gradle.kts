plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlinx.kover)
}

tasks.koverMergedHtmlReport {
    excludes = listOf("com.livefront.sealedenum.compilation.*")
}
tasks.koverMergedXmlReport {
    excludes = listOf("com.livefront.sealedenum.compilation.*")
}
