import magik.createGithubPublication
import magik.github

plugins {
    id("elect86.magik")
    `java-platform`
    `maven-publish`
}

version = rootProject.version
group = rootProject.gradle

tasks {
    withType<JavaCompile> {
        targetCompatibility = "1.8"
        sourceCompatibility = "1.8"
    }
}

dependencies {
    constraints {
        println("[platform-dynamic] applying constraints, $version")
    }
}

publishing {
    publications.createGithubPublication {
        from(components["java"])
        suppressPomMetadataWarningsFor("apiElements")
    }
    repositories {
        github {
            domain = "kotlin-graphics/mary"
        }
    }
}