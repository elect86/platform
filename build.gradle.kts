import java.io.ByteArrayOutputStream

//plugins {
//    java
//    //    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.1.1"
//}

val gitDescribe: String
    get() = ByteArrayOutputStream().also { exec { commandLine("git", "describe", "--tags"); standardOutput = it; } }
        .toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")

version = "0.2.8+22" // for ::bump

subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")

    group = "kotlin.graphics.platform"
    version = rootProject.version

    extensions.configure(PublishingExtension::class) {
        publications.create<MavenPublication>("maven") {
            from(components["javaPlatform"])
        }
        repositories.maven {
            url = uri("../mary")
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
    register("1)bump,commit,push") {
        group = "kx-dev"
        doLast {
            bump()
            exec { commandLine("git", "add", ".") }
            var message = gitDescribe.substringBeforeLast('-')
            val commits = message.substringAfterLast('-').toInt() + 1
            message = message.substringBeforeLast('-') + "-$commits"
            exec { commandLine("git", "commit", "-m", message) }
            exec { commandLine("git", "push") }
        }
    }
    register("2)publish") {
        group = "kx-dev"
        finalizedBy(getTasksByName("publish", true))
    }
    register("3)[mary]commit,push") {
        group = "kx-dev"
        doLast {
            val maryDir = file("../mary")
            exec {
                workingDir = maryDir
                commandLine("git", "add", ".")
            }
            val message = """
                |$project :arrow_up:
                |snapshot $gitDescribe
            """.trimMargin()
            exec {
                workingDir = maryDir
                commandLine("git", "commit", "-m", message)
            }
            exec {
                workingDir = maryDir
                commandLine("git", "push")
            }
        }
    }
}

fun bump() {
    val text = buildFile.readText()
    val version = version.toString()
    val plus = version.indexOf('+')
    buildFile.writeText(text.replace(version, when {
        plus != -1 -> {
            val (tag, delta) = version.split('+')
            "$tag+%02d".format(delta.toInt() + 1)
        }
        else -> "$version+01"
    }))
}