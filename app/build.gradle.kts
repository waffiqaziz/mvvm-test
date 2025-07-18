plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.hilt)
  alias(libs.plugins.ksp)
  id("androidx.navigation.safeargs.kotlin")
  id("kotlin-parcelize")
}

android {
  namespace = "com.waffiq.mvvmexample"
  compileSdk = 35
  buildFeatures.buildConfig = true

  defaultConfig {
    applicationId = "com.waffiq.mvvmexample"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    // BASE URL
    buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    viewBinding = true
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.navigation.fragment.ktx)
  implementation(libs.androidx.navigation.ui.ktx)

  implementation(libs.hilt.android)
  implementation(libs.androidx.activity)
  ksp(libs.hilt.android.compiler)

  implementation(libs.retrofit)
  implementation(libs.retrofit.converter.moshi)
  implementation(libs.moshi.kotlin)
  implementation(libs.okhttp.logging.interceptor)
  ksp(libs.moshi.kotlin.codegen)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}