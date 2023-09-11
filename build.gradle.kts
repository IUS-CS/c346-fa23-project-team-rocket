//List of plugins used by the software
plugins {

    `java-library`
}



//Version of the software
version = "prototype"
//Generic description of the software
description = "Animal Simulation project"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
//Repositories used by libraries and other imported files
repositories {
    mavenCentral()
}

//Dependencies of the software
dependencies {

}

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