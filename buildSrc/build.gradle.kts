plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

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

    implementation("com.android.tools.build:gradle:7.0.0-alpha04")
    implementation(kotlin("gradle-plugin", "1.4.21"))
    implementation(kotlin("android-extensions"))
}
