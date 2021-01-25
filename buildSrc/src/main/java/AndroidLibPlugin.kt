
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

class AndroidLibPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("com.android.library")
        project.plugins.apply("kotlin-android")

        // Configure common android build parameters.
        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            androidExtension.apply {
                compileSdkVersion(30)
                defaultConfig {
                    targetSdkVersion(30)
                    minSdkVersion(23)

                    versionCode = 1
                    versionName = "1.0.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                compileOptions.apply {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        freeCompilerArgs = freeCompilerArgs + listOf(
                            "-Xopt-in=kotlin.Experimental",
                            "-Xopt-in=kotlin.RequiresOptIn",
                            "-Xallow-jvm-ir-dependencies",
                            "-Xskip-prerelease-check"
                        )
                        jvmTarget = "1.8"
                    }
                }
                val sourcesSets = androidExtension.sourceSets.getByName("main")
                val srcDirs = sourcesSets.java.srcDirs
                mutableSetOf<File>().apply {
                    addAll(srcDirs)
                    add (File("src/main/kotlin"))
                }.also { sourcesSets.java.setSrcDirs(it) }
            }
        }
        project.dependencies {
            add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}")
            add("implementation", "androidx.core:core-ktx:${Versions.core_ktx_version}")
            add("implementation", "org.koin:koin-android:${Versions.koin_version}")
            add("implementation", "org.koin:koin-androidx-viewmodel:${Versions.koin_version}")
            add("implementation", "org.koin:koin-core:${Versions.koin_version}")
            add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}")

            add("testImplementation", "junit:junit:4.12")
            add("androidTestImplementation", "androidx.test.ext:junit:1.1.1")
            add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.2.0")
        }
    }
}
