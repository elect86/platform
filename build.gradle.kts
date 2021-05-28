import magik.MagikExtension
import magik.github

plugins {
    id("kx.snapshot") version "0.0.5"
    //    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.1.1"
    id("elect86.magik") version "0.0.4" apply false
}

version = "0.2.8+46" // for ::bump

subprojects {

    val workaround = name.startsWith("platform-")
    //    println("$this, $workaround")
    //    if (workaround)
    //        apply(plugin = "org.gradle.kotlin.kotlin-dsl")
    //    else
    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")
    apply(plugin = "elect86.magik")

    group = "kotlin.graphics.platform"
    version = rootProject.version

    extensions.configure(MagikExtension::class) {
        gitOnPath.set(false)
        dryRun.set(true)
    }

    extensions.configure(PublishingExtension::class) {
        ////        if (workaround)
        publications.create<MavenPublication>("maven") {
            from(components["javaPlatform"])
            suppressPomMetadataWarningsFor("apiElements")
        }
        //        repositories.maven {
        //            name = "prova"
        //            //            url = uri("$rootDir/../mary")
        //            url = uri("$rootDir/repo")
        //        }
        repositories.github {
            domain = ""
            url = uri(rootDir.resolve("repo"))
        }
    }
}