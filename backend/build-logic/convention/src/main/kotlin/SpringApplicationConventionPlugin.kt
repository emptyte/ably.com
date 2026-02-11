import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class SpringApplicationConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      apply(plugin = "katharsis.spring.base")

      apply(plugin = "org.springframework.boot")

      dependencies {
        "implementation"(project(":common"))

        "implementation"(libs.findLibrary("spring.docs.openapi.ui").get())
        "implementation"(libs.findLibrary("spring.boot.starter.flyway").get())
        "implementation"(libs.findLibrary("flyway.postgresql").get())

        "implementation"(libs.findLibrary("keycloak.admin.client").get())

        "runtimeOnly"(libs.findLibrary("postgresql").get())

        "developmentOnly"(libs.findLibrary("spring.boot.devtools").get())
      }
    }
  }
}
