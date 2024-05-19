plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.dopamine"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web") // 웹 애플리케이션 지원
	implementation("org.springframework.boot:spring-boot-starter-security") // 스프링 시큐리티
	implementation("org.springframework.security:spring-security-oauth2-client") // OAuth2 클라이언트 지원
	implementation("org.springframework.security:spring-security-oauth2-jose") // JWT 지원
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-log4j2")	// log4j2

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

configurations.all {
	exclude(group= "org.springframework.boot", module= "spring-boot-starter-logging")
	exclude(group= "org.springframework.boot", module= "logback-classic")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
