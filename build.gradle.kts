import java.io.ByteArrayOutputStream

plugins {
    java
    //    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.1.1"
}

val gitDescribe: String
    get() {
        val stdout = ByteArrayOutputStream()
        rootProject.exec {
            commandLine("git", "describe", "--tags")
            standardOutput = stdout
        }
        return stdout.toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")
    }


subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")

    group = "kotlin.graphics.platform"
    version = "0.2.8"
    //    version = gitDescribe

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
            var message = gitDescribe.substringBeforeLast('-')
            val commits = message.substringAfterLast('-').toInt() + 1
            message = message.substringBeforeLast('-') + "-$commits"
            rootProject.exec { commandLine("git", "commit", "-m", message) }
            rootProject.exec { commandLine("git", "push") }
        }
    }
    register("publishSnapshot") {
        group = "kx"
        doLast {
            subprojects { version = gitDescribe }
            println("publish")
        }
        dependsOn("commit&push")
        finalizedBy(":plugin:publish",
                    ":source:publish",
                    ":test:publish",
                    "commit&pushMary")
    }
    register("commit&pushMary") {
        group = "kx"
        doLast {
            val maryDir = file("$rootDir/../mary")
            rootProject.exec {
                workingDir = maryDir
                commandLine("git", "add", ".")
            }
            rootProject.exec {
                workingDir = maryDir
                commandLine("git", "commit", "-m", gitDescribe.substringBeforeLast('-'))
            }
            rootProject.exec {
                workingDir = maryDir
                commandLine("git", "push")
            }
        }
    }
}
