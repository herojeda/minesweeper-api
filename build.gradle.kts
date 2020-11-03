import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.github.johnrengelman.shadow") version "5.0.0"
    idea
    java
    application
}

val group = "org.hojeda"
val version = "1.0-SNAPSHOT"

project.group = group
project.version = version
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

// Versions
val junitVersion = "5.4.0"
val sparkJavaVersion = "2.9.2"
val guiceVersion = "4.2.3"
val log4j2Version = "2.13.3"
val jacksonVersion = "2.11.2"

dependencies {

    // Spark
    implementation("com.sparkjava:spark-core:$sparkJavaVersion") {
        exclude(group = "com.sparkjava", module = "httpclient")
        exclude(group = "org.slf4j", module = "slf4j-api")
    }

    // Database
    implementation("org.flywaydb:flyway-core:6.1.3")
    implementation("com.zaxxer:HikariCP:3.4.1")
    implementation("com.h2database:h2:1.4.197")
    implementation("commons-dbutils:commons-dbutils:1.7")

    // IoC
    implementation("com.google.inject:guice:$guiceVersion")

    // Log
    implementation("org.apache.logging.log4j:log4j-api:$log4j2Version")
    implementation("org.apache.logging.log4j:log4j-core:$log4j2Version")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4j2Version")

    // Serializer
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")

    // Utils
    implementation("org.apache.commons:commons-lang3:3.1")

    // Test dependencies
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("com.konghq:unirest-java:3.11.01")
    testImplementation("org.hamcrest:hamcrest-all:1.3")

}

// Test config
tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        lifecycle {
            events = mutableSetOf(org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED, org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED, org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED)
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
            showStandardStreams = true
        }
        info.events = lifecycle.events
        info.exceptionFormat = lifecycle.exceptionFormat
    }

    addTestListener(object : TestListener {
        override fun beforeSuite(suite: TestDescriptor) {}
        override fun beforeTest(testDescriptor: TestDescriptor) {}
        override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {}

        override fun afterSuite(suite: TestDescriptor, result: TestResult) {
            if (suite.parent == null) { // root suite
                logger.lifecycle("----")
                logger.lifecycle("Test result: ${result.resultType}")
                logger.lifecycle(
                    "Test summary: ${result.testCount} tests, " +
                        "${result.successfulTestCount} succeeded, " +
                        "${result.failedTestCount} failed, " +
                        "${result.skippedTestCount} skipped"
                )
            }
        }
    })
}

// Application
val mainClass: String = "org.hojeda.minesweeper.configuration.MainApplication"

application {
    this.mainClassName = mainClass
}

tasks.shadowJar {
    archiveBaseName.set("minesweeper")
    archiveClassifier.set("shadow")
    mergeServiceFiles()
    manifest {
        attributes(mapOf("Main-Class" to mainClass))
    }
}