
pluginManagement {
    repositories {
        //        mavenLocal()
        gradlePluginPortal()
        //        maven("https://repo.repsy.io/mvn/elect/kx/")
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")

//        includeBuild("../magik")
    }
}

rootProject.name = "platforms"

gradle.rootProject {
    group = "kotlin.graphics"
    version = "0.3.8"
}

include("source")
include("test")
include("plugin")