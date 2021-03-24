
plugins {
    id("kx.snapshot") version "0.0.5"
//    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.1.1"
}

version = "0.2.8+40" // for ::bump

subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")

    group = "kotlin.graphics.platform"
    version = rootProject.version

    extensions.configure(PublishingExtension::class) {
        publications.create<MavenPublication>("maven") {
            from(components["javaPlatform"])
            suppressPomMetadataWarningsFor("apiElements")
        }
        repositories.maven {
            url = uri("$rootDir/../mary")
            //            name = "scijava"
            //            url = uri("https://maven.scijava.org/content/repositories/releases")
            //            name = "repsy"
            //            url = uri("https://repo.repsy.io/mvn/elect/kx")
            //            name = "aws"
            //            url = uri("https://kx-995066660206.d.codeartifact.eu-central-1.amazonaws.com/maven/mary/")
            //            credentials(PasswordCredentials::class)
            //            url = uri("artifactregistry://europe-west6-maven.pkg.dev/galvanized-case-306920/kx")
        }
    }
}