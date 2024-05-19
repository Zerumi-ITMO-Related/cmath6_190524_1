plugins {
    application
    kotlin("jvm") version "1.9.23"
}

group = "io.github.zerumi"
version = "1.0-SNAPSHOT"

application {
    mainClass = "io.github.zerumi.MainKt"
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.4.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}