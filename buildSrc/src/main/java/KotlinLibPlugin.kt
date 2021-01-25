import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSet.MAIN_SOURCE_SET_NAME
import org.gradle.kotlin.dsl.dependencies
import java.io.File

class KotlinLibPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("java-library")
        project.plugins.apply("kotlin")

        val sourcesSets = project.extensions.getByName("sourceSets")
        if (sourcesSets is SourceSet) {
            if (sourcesSets.name == MAIN_SOURCE_SET_NAME) {
                val srcDirs = sourcesSets.java.srcDirs
                srcDirs.add(File("src/main/kotlin"))
                sourcesSets.java.setSrcDirs(srcDirs)
            }
        }

        project.dependencies {
            add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}")
            add("implementation", "org.koin:koin-core:${Versions.koin_version}")
            add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}")

            add("testImplementation", "junit:junit:${Versions.junit_version}")
        }
    }
}
