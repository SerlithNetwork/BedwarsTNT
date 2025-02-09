plugins {
    kotlin("jvm") version "2.1.20-Beta2"
    id("com.gradleup.shadow") version "8.3.1"
}

group = "net.serlith.bedwarstnt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://repo.codemc.io/repository/maven-releases/") {
        name = "codemc-releases"
    }

    maven("https://repo.codemc.io/repository/maven-snapshots/") {
        name = "codemc-snapshots"
    }
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    implementation("com.github.cryptomorin:XSeries:13.0.0")
}

val targetJavaVersion = 8
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.shadowJar {
    minimize()
    archiveClassifier.set("")

    relocate("com.github.cryptomorin", "net.serlith.bedwarstnt.lib.cryptomorin")
}

tasks.jar {
    archiveClassifier.set("cache")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
