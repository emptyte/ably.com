plugins {
  alias(libs.plugins.katharsis.spring.library)
}

dependencies {
  compileOnly(libs.findLibrary("keycloak.admin.client").get())
}
