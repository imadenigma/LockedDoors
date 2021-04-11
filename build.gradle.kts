
plugins {
    kotlin("jvm") version "1.4.32"
}

group = "me.imadenigma"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    compile("org.spongepowered:configurate-yaml:4.0.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation(kotlin("test-junit5"))
    implementation("me.mattstudios.utils:matt-framework-gui:2.0.2")
}





