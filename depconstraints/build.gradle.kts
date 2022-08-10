plugins {
    id("java-platform")
    id("maven-publish")
}

val core = "1.7.0"
val coroutines = "1.3.9"
val espresso = "3.4.0"
val extJunit = "1.1.3"
val junit = "4.13.2"
val timber = "5.0.1"
val truth = "1.1.3"

dependencies {
    constraints {
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.COROUTINES}:$coroutines")
        api("${Libs.ESPRESSO_CORE}:$espresso")
        api("${Libs.EXT_JUNIT}:$extJunit")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.TIMBER}:$timber")
        api("${Libs.TRUTH}:$truth")
    }
}

publishing {
    publications {
        create<MavenPublication>("dAppsPlatform") {
            from(components["javaPlatform"])
        }
    }
}