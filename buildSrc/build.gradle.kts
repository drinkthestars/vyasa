plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

val kotlinVersion = "1.3.71"

gradlePlugin {
    plugins {
        register("android-lib-plugin") {
            id = "android-lib-plugin"
            implementationClass = "AndroidLibPlugin"
        }
        register("kotlin-lib-plugin") {
            id = "kotlin-lib-plugin"
            implementationClass = "KotlinLibPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:4.1.0-alpha09")
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation(kotlin("android-extensions"))
}
