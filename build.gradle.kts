import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

val pluginGroup = "com.redfootdev"
val pluginPath = "example"
val pluginName = "ExamplePlugin"
val pluginPrefix = "EX"

val pluginAuthors = listOf("Ajaxan")
val pluginDescription = "This plugin is an example"

val pluginVersion = "0.2-SNAPSHOT"
val pluginMinecraftVersion = "1.20.2"
val pluginApiVersion = "1.20"

val pluginDependencies = listOf("kotlin-stdlib")

plugins {
    kotlin("jvm") version "1.9.10"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

version = pluginVersion
group = pluginGroup
val copyJarLocation: String by project

repositories {
    mavenCentral()
    maven(url = "https://papermc.io/repo/repository/maven-public/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:$pluginMinecraftVersion-R0.1-SNAPSHOT")
    shadow("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")

    library("com.google.code.gson", "gson", "2.10.1") // All platform plugins
    bukkitLibrary("com.google.code.gson", "gson", "2.10.1") // Bukkit only
    testImplementation(kotlin("test"))
}

kotlin { // Extension for easy setup
    jvmToolchain(17)
}


tasks.register<Copy>("copyJarToServer") {
    dependsOn("shadowJar")
    from("build/libs/${project.name}-$pluginVersion-all.jar")
    into(copyJarLocation)
}

tasks.build {
    dependsOn("copyJarToServer")
}

tasks.test {
    useJUnitPlatform()
}



application {
    mainClass.set("MainKt")
}
// Github for the guide on this gradle plugin: https://github.com/Minecrell/plugin-yml
bukkit {
    name = pluginName
    version = pluginVersion
    description = pluginDescription
    main = "$pluginGroup.$pluginPath.$pluginName"
    apiVersion = pluginApiVersion

    // Other possible properties from plugin.yml (optional)
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP // or POSTWORLD
    authors = pluginAuthors
    //depend = pluginDependencies
    //softDepend = listOf("Essentials")
    //loadBefore = listOf("BrokenPlugin")
    prefix = pluginPrefix
    //defaultPermission = BukkitPluginDescription.Permission.Default.OP // TRUE, FALSE, OP or NOT_OP
    //provides = listOf("TestPluginOldName", "TestPlug")

    commands {
        register("example") {
            description = "An Example Command!"
            usage = "/example"
            //aliases = listOf("t")
            //permission = "testplugin.test"
            // permissionMessage = "You may not test this command!"
        }
    }
}

