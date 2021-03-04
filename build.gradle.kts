
subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")

    group = "kx.platform"
    version = "0.0.2"

    // limited dsl support inside here
    fun Project.publishing(configure: Action<PublishingExtension>): Unit =
        (this as ExtensionAware).extensions.configure("publishing", configure)

    publishing {
        publications.create<MavenPublication>("maven") {
            from(components["javaPlatform"])
        }
        repositories.maven {
            name = "repsy"
            url = uri("https://repo.repsy.io/mvn/elect/kx")
            credentials(PasswordCredentials::class)
        }
    }
}