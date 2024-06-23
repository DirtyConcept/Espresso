plugins {
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.1.0")
    implementation("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.4")

    compileOnly("org.mongodb:mongodb-driver-sync:5.1.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testCompileOnly("org.jetbrains:annotations:24.1.0")
}


group = "dev.sadghost"
version = "1.0.2"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}