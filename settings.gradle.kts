
pluginManagement {
    repositories {
        //        mavenLocal()
        gradlePluginPortal()
        //        maven("https://repo.repsy.io/mvn/elect/kx/")
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }
}

rootProject.name = "platforms"

include("source")
include("test")
include("plugin")

includeBuild("../magik")