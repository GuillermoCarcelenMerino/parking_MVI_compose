plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.library)
    alias(libs.plugins.ksp.library)

}

android {
    namespace = "com.neo.core"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField ("String", "NEO_PARK_BASE_URL", "\"http://10.0.2.2:8080/\"")
        }

        debug{
            buildConfigField ("String", "NEO_PARK_BASE_URL", "\"http://10.0.2.2:8080/\"")

        }
    }
    buildFeatures.buildConfig = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //hilt && ksp
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //retrofit
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    //Data Store
    implementation(libs.androidx.datastore.preferences)



}