plugins {
  alias(libs.plugins.katharsis.spring.library)
}

dependencies {
  compileOnly(project(":common"))
}
