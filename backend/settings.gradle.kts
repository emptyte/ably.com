pluginManagement {
  includeBuild("build-logic")

  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

  repositories {
    mavenCentral()
  }
}

rootProject.name = "ably"

sequenceOf(
  "common",
  "app"
).forEach {
  include(":$it")
  project(":$it").projectDir = file(it)
}

sequenceOf(
  "organization",
  "identity"
).forEach {
  include(":$it-module")
  project(":$it-module").projectDir = file("module/$it")
}
