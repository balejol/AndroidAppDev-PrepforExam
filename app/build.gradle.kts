plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.egzaminui"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.egzaminui"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //new
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = ["room.schemaLocation": "$projectDir/schemas".toString()];
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    def room_version = "2.2.5"
    def lifecycle_version = "2.2.0"
    def arch_version = "2.1.0"

    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")

    //room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("com.androidx.support.constraint:constraint-layout:1.1.3")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    androidTestImplementation("androidx.room:room-testing:$room_version")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    androidTestImplementation("androidx.arch.core:core-testing:$arch_version")

    implementation("com.github.sundeepk:compact-calendar-view:3.0.0") // Lecture 4

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}