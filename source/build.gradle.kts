// allow the definition of dependencies to other platforms like the Spring Boot BOM
extensions.getByName<JavaPlatformExtension>("javaPlatform").allowDependencies()

dependencies {

    api(platform("org.lwjgl:lwjgl-bom:3.2.3"))

    constraints {
        //        api("org.apache.juneau:juneau-marshall:8.2.0")
        //        api(platform(":kx"))

        val uno = "0.7.9+35"
        for ((art, vers) in mapOf(
            "unsigned" to "3.2.9+31",
            "kool" to "0.9.0+23",
            "glm" to "0.9.9.1-3+23",
            "gli" to "0.8.3.0-16+22",
            "gln" to "0.5.2+22",
            "vkk" to "0.3.2+36",
            "uno" to uno,
            "uno-awt" to uno,
            "uno-core" to uno,
            "uno-vk" to uno,
                                 ))
            api("kotlin.graphics:$art:$vers")
        //
        //        api("$kx:unsigned:")
        //        api("$kx:kool:0.9.0+20")
        //        api("$kx:glm:8dae70c1")
        //        api("$kx:gli:12cd3342")
        //        api("$kx:gln:9eae85c8")
        //        api("$kx:vkk:9d1cd859")
    }
}
