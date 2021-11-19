
plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    val magikVersion = "0.2.5"
    implementation("elect86.magik:elect86.magik.gradle.plugin:$magikVersion")
}