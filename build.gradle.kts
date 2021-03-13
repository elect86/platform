import java.io.ByteArrayOutputStream

plugins {
    //    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.1.1"
}

val gitDescribe: String by lazy {
    val stdout = ByteArrayOutputStream()
    rootProject.exec {
        commandLine("git", "describe", "--tags")
        standardOutput = stdout
    }
    stdout.toString().trim().replace("-g", "-")
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
        //        repositories.maven {
        //            //            name = "scijava"
        //            //            url = uri("https://maven.scijava.org/content/repositories/releases")
        //            //            name = "repsy"
        //            //            url = uri("https://repo.repsy.io/mvn/elect/kx")
        //            //            name = "aws"
        //            //            url = uri("https://kx-995066660206.d.codeartifact.eu-central-1.amazonaws.com/maven/mary/")
        //            //            credentials(PasswordCredentials::class)
        //            //            url = uri("artifactregistry://europe-west6-maven.pkg.dev/galvanized-case-306920/kx")
        //        }
        repositories.maven("$rootDir/../mary")
    }
}

tasks {
    //    val publish by registering {
    //        group = "publishing"
    //        doLast {
    //            println("doLast")
    //        }
    //        finalizedBy("test")
    //    }
    //    val test by registering {
    //        doLast { println("test")}
    //    }
    register("commit&push") {
        group = "kx"
        doLast {
            rootProject.exec { commandLine("git", "add", ".") }
            rootProject.exec { commandLine("git", "commit", "-m", "\"up\"") }
            rootProject.exec { commandLine("git", "push") }
            //            rootProject.exec { commandLine("pwd") }
        }
    }
    register("push") {
        group = "kx"
        //        project.exec {
        //            commandLine("git ")
        //        }
    }
    //    val publish by registering {
    //        doLast { println("publish")}
    //
    //    }
    register("test") {
        group = "kx"
        doLast { println("test") }
        dependsOn("publish")
    }
}
