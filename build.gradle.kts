import magik.SoftwareComponent.javaPlatform
import magik.alsoSnapshot
import magik.github

plugins {
//    id("kx.snapshot") version "0.0.5"
//    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.1.1"

    id("elect86.magik") version "0.0.8" apply false
}

version = "0.2.9" // for magik

subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")
    apply(plugin = "elect86.magik")

    group = "kotlin.graphics.platform"
    version = rootProject.version

    extensions.configure<PublishingExtension> {
        publications.create<MavenPublication>("maven") {
            from(components["javaPlatform"])
            suppressPomMetadataWarningsFor("apiElements")
        }.alsoSnapshot(component = javaPlatform)
//        repositories.maven {
//            url = uri("$rootDir/../mary")
//            //            name = "scijava"
//            //            url = uri("https://maven.scijava.org/content/repositories/releases")
//            //            name = "repsy"
//            //            url = uri("https://repo.repsy.io/mvn/elect/kx")
//            //            name = "aws"
//            //            url = uri("https://kx-995066660206.d.codeartifact.eu-central-1.amazonaws.com/maven/mary/")
//            //            credentials(PasswordCredentials::class)
//            //            url = uri("artifactregistry://europe-west6-maven.pkg.dev/galvanized-case-306920/kx")
//        }
        repositories.github {
            domain = "kotlin-graphics/mary"
        }
    }
}