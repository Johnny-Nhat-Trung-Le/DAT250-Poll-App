import com.github.gradle.node.npm.task.NpmTask

plugins {
	java
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("application")
	id("com.github.node-gradle.node") version "7.0.2"
	jacoco
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "A simple REST API for Poll app using Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

node {
	version = "22.0.0"
	npmVersion = "10.5.1"
	download = true
}

application {
	mainClass = "com/example/demo/PollApplication"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.6.0")
	implementation("org.projectlombok:lombok:1.18.38")

	implementation("org.hibernate.orm:hibernate-core:7.1.1.Final")
	implementation("com.h2database:h2:2.3.232")
	implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")

	implementation("redis.clients:jedis:6.2.0")

	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
	dependsOn(tasks.test)
}

tasks.jacocoTestReport {
	reports {
		xml.required = false
		csv.required = false
		html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
	}
}

tasks.register<NpmTask>("runBuild") {
	args = listOf("run", "build")
	workingDir = file(".")
}

tasks.register<Copy>("copyWebApp") {
	from("dist")
	into("../backend/src/main/resources/static")
	dependsOn("runBuild")
}
