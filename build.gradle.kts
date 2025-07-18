plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.hilt) apply false
  alias(libs.plugins.ksp) apply false
}

buildscript {
  repositories {
    google()
  }
  dependencies {
    classpath(libs.androidx.navigation.safe.args.gradle.plugin)
  }
}