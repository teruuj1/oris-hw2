plugins {
    id("java")
    id("application")
    id("war")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val springVersion: String by project
val jakartaVersion: String by project
val hibernateVersion: String by project
val postgresVersion: String by project
val springSecurityVersion: String by project

application {
    mainClass = "ru.itis304.Main"
}

dependencies {
    implementation("org.springframework:spring-webmvc:$springVersion")
    implementation("org.springframework:spring-jdbc:$springVersion")
    implementation("org.springframework:spring-orm:$springVersion")
    implementation("org.springframework:spring-context-support:$springVersion")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:11.0.5")
    implementation("org.springframework.security:spring-security-core:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-web:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-config:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-taglibs:$springSecurityVersion")
    implementation("org.springframework.data:spring-data-jpa:3.4.4")
    implementation("jakarta.servlet:jakarta.servlet-api:$jakartaVersion")
    implementation("org.hibernate:hibernate-core:$hibernateVersion")
//    implementation("org.hibernate:hibernate-entitymanager:$hibernateVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("com.mchange:c3p0:0.10.2")
    implementation("org.freemarker:freemarker:2.3.34")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}