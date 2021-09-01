import magik.createGithubPublication
import magik.github
import org.gradle.kotlin.dsl.`java-platform`
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    id("elect86.magik")
    `java-platform`
    `maven-publish`
}

version = rootProject.version
group = rootProject.group

tasks {
    withType<JavaCompile> {
        targetCompatibility = "1.8"
        sourceCompatibility = "1.8"
    }
}

publishing {
    publications.createGithubPublication {
        from(components["javaPlatform"])
        suppressPomMetadataWarningsFor("apiElements")
    }
    repositories {
        github {
            domain = "kotlin-graphics/mary"
        }
    }
}