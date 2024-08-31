group = "com.nidhin.jooq.demo"
description = "demo for jooq"
version = "1.0.0"

val javaVersion = JavaVersion.VERSION_21
extra["liquibaseVersion"] = "4.28.0"
extra["hypersistenceUtilsVersion"] = "3.7.5"
extra["opexVersion"] = "5.0.16"
extra["liquibaseVersion"] = "4.27.0"

repositories {
       mavenCentral()
}

plugins {
    val kotlinVersion = "2.0.20"

    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("com.gorylenko.gradle-git-properties") version ("2.4.2")
    id("org.liquibase.gradle") version "2.2.2"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("io.hypersistence:hypersistence-utils-hibernate-63:${property("hypersistenceUtilsVersion")}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("io.micrometer:micrometer-core")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    runtimeOnly("org.postgresql:postgresql")

    developmentOnly("org.liquibase:liquibase-core:${property("liquibaseVersion")}")
}

kotlin {
    jvmToolchain(17)
}

tasks {
    bootRun {}
}