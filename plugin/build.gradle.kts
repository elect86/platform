
plugins {
    id("platform")
}

dependencies {

    constraints {

        val kotlinVersion = "1.5.30"
        api("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:$kotlinVersion")
//        api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")

        api("org.jetbrains.dokka:dokka-gradle-plugin:1.5.0")
//        api("org.jetbrains.dokka:dokka-core:1.4.20")

        api("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin:7.0.0")

        val magikVersion = "0.1.9"
        api("elect86.magik:elect86.magik.gradle.plugin:$magikVersion")

        val sourceDynamicVersion = rootProject.version
        api("kx.source-dynamic:kx.source-dynamic.gradle.plugin:$sourceDynamicVersion")
    }
}