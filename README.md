# SimplePlugin
An Intellij IDEA project that build a simple Gradle plugin for android app.

### Description

This project uses [Transform](http://tools.android.com/tech-docs/new-build-system/transform-api) and [ASM](http://asm.ow2.org/) to implement a gradle plugin for android app that instrument some code into onCreate method of MainActivity. Transform provides a method transform to traverse and each .class file and modify some of them. ASM gives us convenient approaches to modify a .class file, such as modify specified method, fields etc. In this project,MainActivity.class is instrumented with a new line of code, which invokes static method `insertedMethod` of class `tech/liutao/evaluateplugin/InsertCode`, and keep all other .class files unmodified. **NOTE** that class `InsertCode` is **NOT** in this project. It's in android projects that use this simple plugin.

### Usage

Open this porject with Intellij IDEA, and execute task uploadArchives by command `./gradlew uploadArchives` or double click that task in Intellij Idea. Then a `repo` directory, which act as a local maven repository that holds the newly created  plugin, would be generated in the root directory of this project. After this, we could build a android app to test this simple plugin, just like [SimplePluginTest](https://github.com/Tao93/SimplePluginTest).

### Note

1. Concrete steps about creating simple gradle plugin could be found in kind Darren's [blogs](https://afterecho.uk/blog/create-a-standalone-gradle-plugin-for-android-a-step-by-step-guide.html).
2. Different from Darren's blog. I commented the line `compile 'org.codehaus.groovy:groovy-all:2.3.11'` in `build.gradle` file to avoid an error of duplicate groovy. The need to comment that line depends the groovy enviroment in OS. 
