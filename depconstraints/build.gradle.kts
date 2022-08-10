plugins {
    id("java-platform")
    id("maven-publish")
}

val core = "1.7.0"
val espresso = "3.4.0"
val extJunit = "1.1.3"
val junit = "4.13.2"

dependencies {
    constraints {
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.EXT_JUNIT}:$extJunit")
        api("${Libs.ESPRESSO_CORE}:$espresso")
    }
}

publishing {
    publications {
        create<MavenPublication>("dAppsPlatform") {
            from(components["javaPlatform"])
        }
    }
}