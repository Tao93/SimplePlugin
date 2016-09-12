# SimplePlugin
An Intellij IDEA project that build a simple Gradle plugin for android app.

### Usage
Open this porject with Intellij IDEA, and execute task uploadArchives by command `./gradlew uploadArchives` or double click that task in Intellij Idea. Then a `repo` directory, which act as a local maven repository that holds the newly created  plugin, would be generated in the root directory of this project. After this, we could build a android app to test this simple plugin, just like [SimplePluginTest](https://github.com/Tao93/SimplePluginTest).

### Note
1. Concrete steps about creating simple gradle plugin could be found in kind Darren's [blogs](https://afterecho.uk/blog/create-a-standalone-gradle-plugin-for-android-a-step-by-step-guide.html).
2. Different from Darren's blog. I commented the line `compile 'org.codehaus.groovy:groovy-all:2.3.11'` in `build.gradle` file to avoid an error of duplicate groovy. The need to comment that line depends the groovy enviroment in OS. 
