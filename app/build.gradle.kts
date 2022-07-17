plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("io.arrow-kt:arrow-core:1.0.1")
    implementation("org.apache.commons:commons-lang3:3.12.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.7.10")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
}

tasks.test {
    useJUnitPlatform()
}