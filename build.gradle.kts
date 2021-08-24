import magik.github

plugins {
    //    id("kx.snapshot") version "0.0.5"
    //    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.1.1"

    id("elect86.magik") version "0.0.8" apply false
}

version = "0.3.0" // for magik

subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")
    apply(plugin = "elect86.magik")

    group = "kotlin.graphics.platform"
    version = rootProject.version

    extensions.configure<PublishingExtension> {
        fun MavenPublication.addLockfileForSource() {
            if (this@subprojects.name == "source") {
                val lockfile = file("${this@subprojects.projectDir}/kxLockfile.txt")
                artifact(lockfile).classifier = "lockfile"
            }
        }
        publications.create<MavenPublication>("maven") {
            addLockfileForSource()
            from(components["javaPlatform"])
            suppressPomMetadataWarningsFor("apiElements")
        }.github {
            addSnapshotPublication {
                addLockfileForSource()
            }
        }
        repositories {
            github {
                domain = "kotlin-graphics/mary"
            }
        }
    }
}