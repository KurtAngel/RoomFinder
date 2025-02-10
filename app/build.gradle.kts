plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.roomfinder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.roomfinder"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //coroutine
    implementation (libs.kotlinx.coroutines.android)

    //viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //http client
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    //retrofit and json response converter
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //image rendering
    implementation(libs.glide)

    //pager library
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    //Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //ConstraintLayout
    implementation(libs.androidx.constraintlayout.compose.android)

    //navigation controller
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}