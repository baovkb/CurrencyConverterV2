
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.currencyconverterv2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.currencyconverterv2"
        minSdk = 24
        targetSdk = 35
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(project(Modules.cores))

    implementation(project(Modules.networkBusiness))
    implementation(project(Modules.networkPresentation))
    implementation(project(Modules.networkBusinessApi))

    implementation(project(Modules.landingPresentation))
    implementation(project(Modules.landingBusiness))
    implementation(project(Modules.landingBusinessApi))
    implementation(project(Modules.landingApi))

    implementation(project(Modules.currencyPickerBusiness))
    implementation(project(Modules.currencyPickerBusinessApi))
    implementation((project(Modules.currencyPickerPresentation)))
}