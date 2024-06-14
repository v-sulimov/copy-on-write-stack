plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    alias(libs.plugins.jvm)

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    // Apply the maven-publish plugin to publish to the Maven repository.
    `maven-publish`
}

group = "com.vsulimov"
version = "1.0.0"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // Use the JUnit 5 integration.
    testImplementation(libs.junit.jupiter.engine)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Apply a specific Java toolchain to ease working on different environments.
// Also, add javadoc and sources to the maven publication.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withJavadocJar()
    withSourcesJar()
}

// Configure Maven Publication.
publishing {
    publications {
        create<MavenPublication>("publication") {
            artifactId = "copy-on-write-stack"
            from(components["java"])
            pom {
                name = "Copy-on-write stack"
                description = "The Copy-on-write stack represents a last-in-first-out (LIFO) stack of objects."
                url = "https://github.com/v-sulimov/copy-on-write-stack"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
            }
        }
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
