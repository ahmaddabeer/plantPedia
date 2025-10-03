plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.kapt")   // 👈 kapt plugin DSL me aise likhna hai
}

android {
    namespace = "com.example.plantpedia"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.plantpedia"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{

        viewBinding = true


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
}

dependencies {
    implementation("com.github.bumptech.glide:glide:5.0.4")
    implementation("com.github.amitshekhariitbhu.Fast-Android-Networking:android-networking:1.0.4")
    implementation("com.airbnb.android:lottie:6.6.7")
    implementation("com.intuit.sdp:sdp-android:1.1.1")


    implementation ("com.google.mlkit:image-labeling:17.0.9")
    implementation ("com.android.volley:volley:1.2.1")

    // circular image dependency

    implementation("de.hdodenhof:circleimageview:3.1.0")


    val newVersion= "2.7.3"

    implementation("androidx.navigation:navigation-fragment-ktx:$newVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$newVersion")

    // coruteine dependencies

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    val room_version = "2.8.1"

    // Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version") // kapt must

    // Optional: testing
    testImplementation("androidx.room:room-testing:$room_version")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    //contrycodepicker library

    implementation("com.hbb20:ccp:2.5.0")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}