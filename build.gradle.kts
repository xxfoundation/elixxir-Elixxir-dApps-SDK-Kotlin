// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Config.ANDROID_GRADLE_PLUGIN apply false
    id("com.android.library") version Config.ANDROID_GRADLE_PLUGIN apply false
    id("org.jetbrains.kotlin.android") version Config.KOTLIN apply false
    id("org.jetbrains.kotlin.jvm") version Config.KOTLIN apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}