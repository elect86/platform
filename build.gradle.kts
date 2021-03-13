import java.io.ByteArrayOutputStream

plugins {
    java
    //    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.1.1"
}

val gitDescribe: String by lazy {
    val stdout = ByteArrayOutputStream()
    rootProject.exec {
        commandLine("git", "describe", "--tags")
        standardOutput = stdout
    }
    stdout.toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")
}


subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")

    group = "kotlin.graphics.platform"
    //    version = "0.1.9"
    version = gitDescribe

    extensions.configure(PublishingExtension::class) {
        publications.create<MavenPublication>("maven") {
            from(components["javaPlatform"])
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

tasks {
    register("commit&push") {
        group = "kx"
        doLast {
            rootProject.exec { commandLine("git", "add", ".") }
            rootProject.exec { commandLine("git", "commit", "-m", gitDescribe) }
            rootProject.exec { commandLine("git", "push") }
        }
    }
}
