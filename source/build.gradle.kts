
// allow the definition of dependencies to other platforms like the Spring Boot BOM
javaPlatform.allowDependencies()

dependencies {

    api(platform("org.lwjgl:lwjgl-bom:3.2.3"))

    constraints {
//        api("org.apache.juneau:juneau-marshall:8.2.0")
//        api(platform(":kx"))

        val kx = "com.github.kotlin-graphics"

        api("$kx:kotlin-unsigned:ff50c1dd")

        api("$kx:kool:706a42d0")
    }
}
