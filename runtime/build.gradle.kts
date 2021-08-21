plugins {
    id("com.livefront.sealedenum.kotlin")
    id("com.livefront.sealedenum.detekt")
    id("com.livefront.sealedenum.publish")
}

kotlin {
    jvm()
    js(BOTH) {
        browser()
        nodejs()
    }

    macosX64()
    iosArm32(); iosArm64(); iosX64()
    watchosArm32(); watchosArm64(); watchosX86(); watchosX64()

    linuxArm64(); linuxX64()
    mingwX86(); mingwX64()

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
