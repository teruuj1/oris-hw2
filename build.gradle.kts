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

//application {
//    mainClass = "ru.itis304.Main"
//}

dependencies {
    implementation("org.springframework:spring-webmvc:6.2.3")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:11.0.5")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}