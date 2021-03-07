
subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")

    group = "kx.platform"
    version = "0.1.8"

    // limited dsl support inside here
    fun publishing(configure: Action<PublishingExtension>) = extensions.configure("publishing", configure)

    publishing {
        publications.create<MavenPublication>("maven") {
            from(components["javaPlatform"])
        }
        repositories.maven("$rootDir/../mary")
    }
}