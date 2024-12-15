plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.orderfoodapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.orderfoodapp"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0") // Phiên bản mới nhất có thể thay đổi
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("androidx.sqlite:sqlite:2.1.0")
    dependencies {
        implementation ("androidx.recyclerview:recyclerview:1.2.1")
    }


}