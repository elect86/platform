//import org.gradle.kotlin.dsl.support.expectedKotlinDslPluginsVersion

plugins {
    id("my-platform")
}

dependencies {

    constraints {

//        api("org.gradle.kotlin:gradle-kotlin-dsl-plugins:$expectedKotlinDslPluginsVersion")
        api("org.jetbrains.kotlin:kotlin-gradle-plugin:$embeddedKotlinVersion")
//        api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")

        api("org.jetbrains.dokka:dokka-gradle-plugin:1.5.0")
//        api("org.jetbrains.dokka:dokka-core:1.4.20")

//        api("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin:7.0.0")

        val magikVersion = "0.2.6"
        api("elect86.magik:elect86.magik.gradle.plugin:$magikVersion")

        api("kx.source-dynamic:kx.source-dynamic.gradle.plugin:${rootProject.version}")
    }
}