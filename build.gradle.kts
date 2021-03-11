import java.io.ByteArrayOutputStream

val gitDescribe: String by lazy {
        val stdout = ByteArrayOutputStream()
        rootProject.exec {
            commandLine("git", "describe", "--tags")
            standardOutput = stdout
        }
        stdout.toString().trim()
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
        repositories.maven {
            //            name = "scijava"
            //            url = uri("https://maven.scijava.org/content/repositories/releases")
            name = "repsy"
            url = uri("https://repo.repsy.io/mvn/elect/kx")
            credentials(PasswordCredentials::class)
        }
    }
}