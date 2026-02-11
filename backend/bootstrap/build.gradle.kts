plugins {
  alias(libs.plugins.katharsis.spring.application)
}

dependencies {
  sequenceOf(
    "organization",
    "identity"
  ).forEach {
    implementation(project(":$it-module"))
  }
}
