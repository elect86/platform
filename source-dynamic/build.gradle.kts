plugins {
    kotlin("jvm") //version "1.5.30"
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {

    val magikVersion = "0.1.7"
    implementation("elect86.magik:elect86.magik.gradle.plugin:$magikVersion")
}