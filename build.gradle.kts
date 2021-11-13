plugins {
    kotlin("jvm") version "1.4.32"
    application
}
group = "com.jtschwartz"
version = "0.0.1"

val tornadoFxVersion: String by rootProject

repositories {
    mavenCentral()
}

application {
    mainClassName = "com.jtschwartz.scratchpad.ScratchpadKt"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31")
    implementation("no.tornado:tornadofx:$tornadoFxVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.31")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
