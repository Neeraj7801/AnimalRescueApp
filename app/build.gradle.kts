plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {

    namespace = "com.example.animal_project"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.animal_project"
        minSdk = 24
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
        viewBinding = true
    }
}

dependencies {
    // Core Android libraries
    implementation(libs.androidx.core.ktx.v1101)
    implementation(libs.androidx.appcompat.v161)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout.v214)

    // Firebase
    implementation(libs.firebase.auth.v2211)
    implementation(libs.firebase.database.v2022)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx.v253)
    implementation(libs.androidx.navigation.ui.ktx.v253)

    // DrawerLayout and RecyclerView
    implementation(libs.androidx.drawerlayout)
    implementation(libs.androidx.recyclerview.v131)

    // Location Services
    implementation(libs.play.services.location.v2101)

    // Unit Testing
    testImplementation(libs.junit)

    // Android Instrumentation Testing
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
}
