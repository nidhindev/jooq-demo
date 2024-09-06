import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jooq.meta.jaxb.MatcherTransformType

group = "com.nidhin.jooq.demo"
description = "demo for jooq"
version = "1.0.0"

val javaVersion = JavaVersion.VERSION_21
extra["liquibaseVersion"] = "4.28.0"
extra["hypersistenceUtilsVersion"] = "3.7.5"
extra["opexVersion"] = "5.0.16"
extra["liquibaseVersion"] = "4.27.0"
extra["jooqVersion"] = "3.19.8"

repositories {
       mavenCentral()
}

plugins {
    val kotlinVersion = "2.0.20"
    id ("org.openapi.generator") version "7.8.0"
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("com.gorylenko.gradle-git-properties") version ("2.4.2")
    id("org.liquibase.gradle") version "2.2.2"
    id("org.jooq.jooq-codegen-gradle") version "3.19.8"

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
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.6.0")
    implementation("org.jooq:jooq:${property("jooqVersion")}")
    implementation("org.jooq:jooq-kotlin:${property("jooqVersion")}")

    runtimeOnly("org.postgresql:postgresql")
    jooqCodegen("org.postgresql:postgresql")

    developmentOnly("org.liquibase:liquibase-core:${property("liquibaseVersion")}")
}

kotlin {
    jvmToolchain(17)
}

tasks {
    bootRun {}
}

jooq {
    configuration {
        generator {
            strategy {
                name = "org.jooq.codegen.DefaultGeneratorStrategy"
                matchers {
                    schemas {
                        schema {
                            schemaClass {
                                transform = MatcherTransformType.PASCAL
                                expression = "$0_Schema"
                            }
                        }
                        tables {
                            table {
                                tableClass {
                                    transform = MatcherTransformType.PASCAL
                                    expression = "$0_TABLE"
                                }
                                pojoClass {
                                    transform = MatcherTransformType.PASCAL
                                    expression = "$0_Entity"
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    executions {
        create("public") {
            configuration {
                jdbc {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5432/jooq-demo-db?reWriteBatchedInserts=true"
                    user = "jooq-demo-user"
                    password = "jooq"
                }
                generator {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        includes = ".*"
                        inputSchema = "public"
                        excludes = "database.*"
                    }
                    target {
                        packageName = "com.nidhin.jooq.demo.jooq"
                        directory = "src/generated/jooq/public"
                    }
                    generate {
                        isPojos = true
                        isPojosAsKotlinDataClasses = true
                        isKotlinNotNullPojoAttributes = true
                        isPojosEqualsAndHashCode = false
                    }
                }
            }
        }
    }
}


val kotlinExtension = project.extensions.getByName("kotlin") as KotlinJvmProjectExtension
kotlinExtension.sourceSets.getByName("main").kotlin.srcDir("$projectDir/src/generated/jooq/public")

openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("$projectDir/src/main/resources/openapi/demo-api-specification.yml")
    apiPackage.set("com.nidhin.jooq.demo.api")
    modelPackage.set("com.nidhin.jooq.demo.api")
    outputDir.set("$projectDir/build/generate-resources")
    modelNameSuffix.set("Dto")
    globalProperties.put("modelDocs", "false")
    globalProperties.put("models", "")
    globalProperties.put("modelTests", "false")
    configOptions.put("sourceFolder","openapi")
    configOptions.put("serializationLibrary","jackson")
    kotlinExtension.sourceSets
        .getByName("main")
        .kotlin.srcDir("$projectDir/build/generate-resources/openapi")
}