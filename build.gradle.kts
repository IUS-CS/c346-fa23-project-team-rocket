//List of plugins used by the software
plugins {
    java
    `java-library`
    jacoco
}


//Group
group = "team.rocket"
//Version of the software
version = "0.5.0"
//Generic description of the software
description = "Animal Simulation project"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
//Repositories used by libraries and other imported files
repositories {
    mavenCentral()
    mavenLocal()
}

//Dependencies of the software
dependencies {
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation(platform("io.cucumber:cucumber-bom:7.14.0"))

        testImplementation ("io.cucumber:cucumber-java:7.14.0")
        testImplementation("io.cucumber:cucumber-junit:7.14.0")
        testImplementation("org.junit.platform:junit-platform-suite")
        testImplementation("org.junit.jupiter:junit-jupiter")
}

//Gradle configuration
configurations {}

val cucumberRuntime by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}

//Gradle Tasks
tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(17)
        options.compilerArgs.add("-Xlint:-deprecation")
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
}

tasks.withType<Test> {
        useJUnitPlatform()
        // Work around. Gradle does not include enough information to disambiguate
        // between different examples and scenarios.
        systemProperty("cucumber.junit-platform.naming-strategy", "long")
}
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "team.rocket.IO.UI"
    }
}
task("cucumber") {
    dependsOn("assemble", "compileTestJava")
    doLast {
        javaexec {
            mainClass.set("io.cucumber.core.cli.Main")
            classpath = cucumberRuntime + sourceSets.main.get().output + sourceSets.test.get().output
            // Change glue for your project package where the step definitions are.
            // And where the feature files are.
            args = listOf("--plugin", "pretty", "--glue", "stepdefinitions", "src/test/resources")
            // Configure jacoco agent for the test coverage.
            val jacocoAgent = zipTree(configurations.jacocoAgent.get().singleFile)
                    .filter { it.name == "jacocoagent.jar" }
                    .singleFile
            jvmArgs = listOf("-javaagent:$jacocoAgent=destfile=$buildDir/results/jacoco/cucumber.exec,append=false")
        }
    }
}