import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.springframework.experimental.aot") version "0.12.1"
}

group = "com.magicpark"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    maven { url = uri("https://repo.spring.io/release") }
    mavenCentral()
}

dependencies {

    // MySQL connector Java
    implementation("mysql:mysql-connector-java:8.0.30")

    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")


    // Spring boot starter data JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.3")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.11")

    implementation("org.springframework.boot:spring-boot-starter-security:2.7.3")


    implementation("org.mariadb.jdbc:mariadb-java-client:2.1.2")

    implementation("org.springframework.security:spring-security-crypto:5.7.3")

    implementation("commons-validator:commons-validator:1.7")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // JWT
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    // Security
    implementation("org.springframework.security:spring-security-core:5.7.3")

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // RxJava
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")

    implementation("com.squareup.okhttp:okhttp:2.7.5")

    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")

    // Spring boot starter web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.session:spring-session-core")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Firebase
    implementation("com.google.firebase:firebase-admin:9.1.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf("BP_NATIVE_IMAGE" to "true")
}
