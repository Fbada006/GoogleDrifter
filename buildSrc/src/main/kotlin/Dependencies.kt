const val kotlinVersion = "1.3.72"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "4.0.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
}

object AndroidSdk {
    const val min = 19
    private const val compile = 30
    const val target = compile
}

object Libraries {
    private object Versions {
        const val core_ktx_version = "1.3.1"
        const val appcompat_version = "1.1.0"
        const val constraintlayout_version = "1.1.3"
        const val permissionsdispatcher_version = "4.7.0"
        const val lifecycle_version = "2.2.0"
        const val location_version = "17.0.0"
        const val activity_version = "1.1.0"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout_version}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.core_ktx_version}"

    //permissions
    const val permisionsDispatcher =
        "org.permissionsdispatcher:permissionsdispatcher:${Versions.permissionsdispatcher_version}"
    const val permisionsDispatcherKapt =
        "org.permissionsdispatcher:permissionsdispatcher-processor:${Versions.permissionsdispatcher_version}"

    //Lifecycle
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"

    //Activity ktx
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity_version}"

    //Location and maps
    const val serviceLocation =
        "com.google.android.gms:play-services-location:${Versions.location_version}"
    const val serviceMaps = ("com.google.android.gms:play-services-maps:${
        Versions.location_version
    }")
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val espresso = "3.2.0"
        const val extJunit = "1.1.1"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val extJunit = ("androidx.test.ext:junit:${Versions.extJunit}")
}