import magik.createGithubPublication
import magik.github

plugins {
    kotlin("jvm") //version "1.5.30"
    `kotlin-dsl`
    id("elect86.magik")
    `maven-publish`
}

version = rootProject.version
group = rootProject.group

repositories {
    mavenCentral()
}

tasks {
    withType<JavaCompile> {
        targetCompatibility = "1.8"
        sourceCompatibility = "1.8"
    }
}

publishing {
    publications.createGithubPublication("pluginMaven") {
//        from(components["java"])
        suppressPomMetadataWarningsFor("apiElements")
    }
    repositories {
        github {
            domain = "kotlin-graphics/mary"
        }
    }
}