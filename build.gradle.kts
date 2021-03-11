import java.io.ByteArrayOutputStream

fun gitDescribe(): String {
    val stdout = ByteArrayOutputStream()
    rootProject.exec {
        commandLine("git", "describe", "--tags")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}


subprojects {

    apply(plugin = "java-platform")
    apply(plugin = "maven-publish")

    group = "kotlin.graphics.platform"
    version = "0.1.9"

    extensions.configure(PublishingExtension::class) {
        publications.create<MavenPublication>("maven") {
            from(components["javaPlatform"])
        }
        repositories.maven {
            name = "scijava"
            url = uri("https://maven.scijava.org/content/repositories/releases")
            credentials(PasswordCredentials::class)
        }
    }
}

println(gitDescribe())