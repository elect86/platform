plugins {
    id("my-platform")
}

// allow the definition of dependencies to other platforms like the Spring Boot BOM
extensions.getByName<JavaPlatformExtension>("javaPlatform").allowDependencies()

dependencies {
//    api(platform("org.lwjgl:lwjgl-bom:3.2.3"))
}