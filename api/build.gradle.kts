import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*

plugins {
	id("org.springframework.boot") version "2.5.0-M1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.springframework.cloud.contract") version "3.0.0"
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

contracts {
	setTestFramework(org.springframework.cloud.contract.verifier.config.TestFramework.JUNIT5)
	setContractsDslDir(file("${project.projectDir}/../contracts/com.mwcaisse.samples/recipe-api/0.0.1-SNAPSHOT/"))
	//setBasePackageForTests("com.mwcaisse.examples.scc.recipeapi.contract")
	//setPackageWithBaseClasses("com.mwcaisse.examples.scc.recipeapi.contract")
	setBaseClassForTests("com.mwcaisse.examples.scc.recipeapi.contract.BaseContractTest")
}
tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "15"
	}
}

tasks.assemble {
	dependsOn("contractTest", "test")
}

tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events(PASSED, FAILED, STANDARD_ERROR, SKIPPED)
		exceptionFormat = FULL
		showExceptions = true
		showCauses = true
		showStackTraces = true
		outputs.upToDateWhen {false}
	}
}
