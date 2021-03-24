import java.io.ByteArrayOutputStream

//plugins {
//    java
//    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.1.1"
//}

version = "0.2.8+36" // for ::bump

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

val gitDescribe: String
    get() = ByteArrayOutputStream().also { exec { commandLine("git", "describe", "--tags"); standardOutput = it; } }.toString().trim()

val gitDistance: Int
    get() = gitDescribe.substringBeforeLast("-g").substringAfterLast('-').toInt() + 1 // the next is the one we are interested in

val gitTag: String
    get() = gitDescribe.substringBeforeLast('-').substringBeforeLast('-')

fun gitAddCommitPush(message: String, dir: File = rootDir) {
    exec { workingDir = dir; commandLine("git", "add", "."); }
    exec { workingDir = dir; commandLine("git", "commit", "-m", message); }
    exec { workingDir = dir; commandLine("git", "push"); }
}

tasks {
    register("1)bump,commit,push") {
        group = "kx-dev"
        doLast {
            bump()
            gitAddCommitPush("$gitTag+$gitDistance")
        }
    }
    register("2)publish") {
        group = "kx-dev"
        //        dependsOn("commit&push") not reliable
        finalizedBy(getTasksByName("publish", true))
    }
    register("3)[mary]commit,push") {
        group = "kx-dev"
        doLast {
            gitAddCommitPush("""
                |$project :arrow_up:
                |snapshot $gitDescribe""".trimMargin(), file("../mary"))
        }
        //        mustRunAfter("publishSnapshot") // order
    }
}

fun bump() {
    val text = buildFile.readText()
    val version = version.toString()
    val bump = "${version.split('+').first()}+%02d".format(gitDistance)
    buildFile.writeText(text.replace(version, bump))
}