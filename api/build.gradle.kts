import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.0-M1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.21-2"
	kotlin("plugin.spring") version "1.4.21-2"
}

group = "com.mwcaisse.examples.scc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

extra["springCloudVersion"] = "2020.0.1-SNAPSHOT"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "15"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
