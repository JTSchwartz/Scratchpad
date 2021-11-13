plugins {
    kotlin("jvm") version "1.4.32"
    application
}
group = "com.jtschwartz"
version = "0.0.1"

val kotlinVersion: String by rootProject
val tornadoFxVersion: String by rootProject

repositories {
    mavenCentral()
}

application {
    mainClassName = "com.jtschwartz.scratchpad.ScratchpadKt"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("no.tornado:tornadofx:$tornadoFxVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
