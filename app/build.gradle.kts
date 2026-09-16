/**
 * Build configuration for the application module of the MemoryCardGame project.
 * This file defines the plugins, Android-specific settings, and project dependencies.
 */
plugins {
    // Standard Android application plugin
    alias(libs.plugins.android.application)
    // Kotlin Compose compiler plugin
    alias(libs.plugins.kotlin.compose)
    // Kotlin Symbol Processing (KSP) for annotation processing
    alias(libs.plugins.ksp)
    // Hilt plugin for automated dependency injection
    alias(libs.plugins.hilt)
}

android {
    // Unique namespace for the generated R and BuildConfig classes
    namespace = "com.art.memorycardgame"
    
    // SDK versions configuration
    compileSdk = 36

    defaultConfig {
        // Unique identifier for the application on the Play Store
        applicationId = "com.art.memorycardgame"
        // Minimum Android version required to run the app
        minSdk = 24
        // The API level that the app is optimized for
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        // Specifies the runner for instrumentation tests
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        // Configuration for release builds
        release {
            // Disables code shrinking, obfuscation, and optimization
            isMinifyEnabled = false
            // ProGuard rules for release builds
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    // Sets Java version compatibility for compilation
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    // Enables support for Jetpack Compose UI toolkit
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core Android and Lifecycle libraries from Version Catalog
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    
    // Jetpack Compose dependencies from Version Catalog
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    
    // Basic testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    
    // Debug tools
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Hilt for Dependency Injection (using Version Catalog)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Additional dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.navigation:navigation-compose:2.8.5")

    // Additional Unit testing libraries
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    testImplementation("io.mockk:mockk:1.14.5")
}
