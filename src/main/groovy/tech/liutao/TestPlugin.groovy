package tech.liutao
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by liutao on 9/9/16.
 */
class TestPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
//        target.extensions.create('tpplugin', TestPluginExtention)


        target.android.registerTransform(new MyTransform())

        target.afterEvaluate {
            target.android.applicationVariants.each { variant ->
                def proguardTask = target.tasks.findByName("transformClassesAndResourcesWithProguardFor${variant.name.capitalize()}")
                if (proguardTask) {
                    target.logger.error "proguard=>${variant.name.capitalize()}"

                    proguardTask.inputs.files.files.each { File file->
                        target.logger.error "file inputs=>${file.absolutePath}"
                    }

                    proguardTask.outputs.files.files.each { File file->
                        target.logger.error "file outputs=>${file.absolutePath}"
                    }
                }

                def dexTask = target.tasks.findByName("transformClassesWithDexFor${variant.name.capitalize()}")
                if (dexTask) {
                    target.logger.error "dex=>${variant.name.capitalize()}"

                    dexTask.inputs.files.files.each { File file->
                        target.logger.error "file inputs=>${file.absolutePath}"
                    }

                    dexTask.outputs.files.files.each { File file->
                        target.logger.error "file outputs=>${file.absolutePath}"
                    }
                }

                def doNothingTask = target.tasks.findByName("transformClassesWithDoNothing${variant.name.capitalize()}")
                if (dexTask) {
                    target.logger.error "dex=>${variant.name.capitalize()}"

                    dexTask.inputs.files.files.each { File file->
                        target.logger.error "file inputs=>${file.absolutePath}"
                    }

                    dexTask.outputs.files.files.each { File file->
                        target.logger.error "file outputs=>${file.absolutePath}"
                    }
                }
            }
        }

//        target.afterEvaluate {
//
//            target.android.applicationVariants.all { variant ->
//                File inputWordFile = new File(target.projectDir, target.extensions.tpplugin.words)
//                File outputDir = new File(target.buildDir, "generated/source/wordsToEnum/${variant.dirName}")
//                def task = target.tasks.create(name: "wordsToEnum${variant.name.capitalize()}", type: WordsToEnumTask)
//                        {
//                            outDir = outputDir
//                            wordsFile = inputWordFile
//                            enumClassName = target.extensions.tpplugin.enumClass
//                            outputPackageName = target.extensions.tpplugin.outputPackage
//                        }
//                variant.registerJavaGeneratingTask task, outputDir
//            }
//        }
    }
}
