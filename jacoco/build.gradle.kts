plugins {
    base
    id("jacoco-report-aggregation")
}

dependencies {
    jacocoAggregation(projects.ksp)
    jacocoAggregation(projects.processingCommon)
    jacocoAggregation(projects.processingTests.kspTests)
    jacocoAggregation(projects.processingTests.processorTests)
    jacocoAggregation(projects.processor)
    jacocoAggregation(projects.runtime)
}

reporting {
    reports {
        val testCodeCoverageReport by creating(JacocoCoverageReport::class) {
            testType.set(TestSuiteType.UNIT_TEST)
        }
    }
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport"))
}
