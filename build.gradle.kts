import no.tornado.fxlauncher.gradle.FXLauncherExtension

buildscript {
	repositories {
		mavenCentral()
	}
}

plugins {
	application
	kotlin("jvm") version "1.4.32"
	id("no.tornado.fxlauncher") version "1.0.20"
}

group = "com.jtschwartz"
version = "1.0.0"

val kotlinVersion: String by rootProject
val tornadoFxVersion: String by rootProject

repositories {
	mavenCentral()
}

application {
	mainClassName = "com.jtschwartz.scratchpad.ScratchpadKt"
}

configure<FXLauncherExtension> {
	applicationVendor = "Jacob Schwartz (jacob@jtschwartz.com)"
	applicationUrl = "https://storage.googleapis.com/jts-scratchpad-bucket"
	applicationMainClass = application.mainClassName
	applicationVersion = "1.0.2"
	applicationTitle = "Scratchpad"
	applicationName = "Scratchpad"
}

dependencies {
	platform("org.jetbrains.kotlin:kotlin-bom")
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

tasks.withType<Jar> {
	manifest {
		attributes["Main-Class"] = application.mainClassName
	}
	from(configurations.compileClasspath.get().map {if (it.isDirectory) it else zipTree(it)})
}

tasks.register<Exec>("deployToGCP") {
	dependsOn("embedApplicationManifest")
	commandLine("gsutil", "cp", "-r", "./build/libs", "gs://jts-scratchpad-bucket")
}
