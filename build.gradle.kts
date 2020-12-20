import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.jetbrains.dokka") version "0.10.1"
    id("com.jfrog.bintray") version "1.8.5"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    `maven-publish`
}

group = "common.marvel"
version = "0.1.10"

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.dokka {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
}

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.dokka)
}

val sourceJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Source"
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
            artifact(sourceJar)
            artifact(dokkaJar)
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
    setPublications("default")
    pkg(
        delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
            userOrg = "commonmarvel"
            repo = "spring-boot-helper-kotlin"
            name = "spring-boot-helper-kotlin"
            websiteUrl = "https://github.com/CommonMarvel/spring-boot-helper-kotlin"
            githubRepo = "CommonMarvel/spring-boot-helper-kotlin"
            vcsUrl = "https://github.com/CommonMarvel/spring-boot-helper-kotlin.git"
            description = ""
            setLabels("kotlin")
            setLicenses("MIT")
            desc = description
        }
    )
}

tasks.getByName("bintrayUpload").enabled = true
tasks.getByName("publish").enabled = false
tasks.getByName("bootJar").enabled = false
tasks.getByName("jar").enabled = true
