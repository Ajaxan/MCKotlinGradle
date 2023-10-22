# Example Kotlin  + Gradle Minecraft Plugin
This plugin is a barebones starting point for developing kotlin plugins using kotlin and gradle. 
I've provided some description for users who are not necessarily familiar with kotlin + gradle, 
but understand programming basics and have likely made a plugin with java in the past.

If you are familiar with Kotlin and/or Gradle or simple understand what these tools do the descriptions
below will not offer you much besides some very basic usage functionality.

## Kotlin
This plugin is written in Kotlin an emerging language that is quickly looking to overtake Java for development in the 
JVM. Kotlin is a free, open source, statically typed language designed for the JVM. Being designed for the JVM
Kotlin can co-exist with Java. Meaning any Java API or Library can be used in a Kotlin project. This means
you get the benefits of Kotlin without abandoning the community of code the Minecraft's Java community provides.

With Kotlin, I do recommend using IntelliJ. They created the language and as a result their IDE has fantastic support 
for it. Including even being able to fully convert Java code into Kotlin. A very useful tool when referencing code 
snippets from stack overflow or spigot forums.

**NOTE:** You will need to include the Kotlin standard library in this project. This is done for you, but it is 
essential for kotlin to work

## Gradle
This plugin uses gradle kotlin. Gradle is a build automation tool used to bring together all your plugin
dependencies, build your plugin.yml, produce your jar file, and place it where you would like it to go.
It can do far more than that, but for your purposes, unless you want to dive in I've tried to provide everything
you'll need for gradle to be a simple single useful command that does all your building for you.

### Dependencies

The only thing you may need to do is add dependencies. Dependencies are essentially other plugins that provide
APIs for you to use to tie into their projects. These dependencies are downloaded by gradle and included in your 
project to be referenced by first telling gradle where to find them. They are often found in the maven repository,
but they can be hosted in a variety of ways. In every case though the authors will provide a way to get the info.

You can see one example of a dependency already in the form of `io.papermc.paper:paper-api`
This is my preferred API for making plugin over spigot or, if you're very old in the scene, straight up bukkit.

To add dependencies you'll need to find the repo from where they can be added and include them as I did with 
the paper API. As an example lets make a Factions dependency by googling "factions maven". 
We find this: https://mvnrepository.com/artifact/com.massivecraft.factions/Factions/2.14.0 on mvnrepository.com. By clicking Gradle (Kotlin) we find our dependency and can put it in our `dependencies {}` section
```kotlin
dependencies {
    implementation("com.massivecraft.factions:Factions:2.14.0")
}
```

If you wish to use a dependency that is not found through an online repo, and you instead want to 
use it from a local .jar file you can do the following:
```kotlin
dependencies {
   implementation(files("libs/something_local.jar"))
}
```

### Jar Copy
Currently, the plugin is configured to automatically copy the jar to a location (preferably your plugin 
folder on your dev server). If you wish to simply manually handle this you can remove this process by 
commenting out the following section of code in the `build.gradle.kts`

```kotlin
tasks.register<Copy>("copyJarToServer") {
    dependsOn("shadowJar")
    from("build/libs/${project.name}-$pluginVersion-all.jar")
    into(copyJarLocation)
}

tasks.build {
    dependsOn("copyJarToServer")
}
```


## Resources
Like with any minecraft project you will need a `plugin.yml`. 
You'll notice most the `gradle.build.kts` file builds your `plugin.yml`.
If you need to change values or modify the plugin.yml please do it from there

If you need to create a `config.yml` or other yml files place them in the 
resources directory in `src/main/resources`

## Build and Run

### Setup
There are still steps you'll need to take this example and make it your own project.
1. Configure your java/kotlin environment.
2. Copy/paste the `sample.gradle.properties` file to `gradle.properties` and configure
   the jar copy location to be where your server is.
3. Change the `gradle.build.kts` information to match your plugin. 
4. Change the project name in the `settings.gradle.kts`
   

### Commands
`./gradlew build` - This builds the project and creates the jar file and copies it to the 
location in the gradle file. 
