plugins {
    id("my-platform")
}

// allow the definition of dependencies to other platforms like the Spring Boot BOM
extensions.getByName<JavaPlatformExtension>("javaPlatform").allowDependencies()

publishing.publications.named<MavenPublication>("maven") {
    val lockfile = file("$projectDir/kxLockfile.txt")
    artifact(lockfile).classifier = "lockfile"
}