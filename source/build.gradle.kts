
// allow the definition of dependencies to other platforms like the Spring Boot BOM
javaPlatform.allowDependencies()

dependencies {

    api(platform("org.lwjgl:lwjgl-bom:3.2.3"))

//    constraints {
//        api("org.apache.juneau:juneau-marshall:8.2.0")
//    }
}
