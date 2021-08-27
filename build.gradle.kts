import magik.github

plugins {
    id("elect86.magik") version "0.1.2" apply false
}

version = "0.3.3"

subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")
    apply(plugin = "elect86.magik")

//    extensions.configure<magik.MagikExtension> { dryRun.set(true) }

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