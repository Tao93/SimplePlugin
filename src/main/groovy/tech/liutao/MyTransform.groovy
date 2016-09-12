package tech.liutao

import com.android.build.api.transform.Context
import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformOutputProvider
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter


/**
 * Created by liutao on 9/9/16.
 */
class MyTransform extends Transform{
    @Override
    String getName() {
        return "DoNothing"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return Collections.singleton(QualifiedContent.DefaultContentType.CLASSES)
    }

    @Override
    Set<QualifiedContent.Scope> getScopes() {
        return Collections.singleton(QualifiedContent.Scope.PROJECT)
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(Context context, Collection<TransformInput> inputs,
                   Collection<TransformInput> referencedInputs,
                   TransformOutputProvider outputProvider, boolean isIncremental) throws IOException,
            TransformException, InterruptedException {

        def outDir = outputProvider.getContentLocation("donothing", outputTypes, scopes, Format.DIRECTORY)

        outDir.deleteDir()
        outDir.mkdirs()

        inputs.each {
            it.directoryInputs.each {
                int pathBitLen = it.file.toString().length()
                it.file.traverse {
                    def path = "${it.toString().substring(pathBitLen)}"

                    if (it.isDirectory()) {
                        new File(outDir, path).mkdirs()
                    } else {
                        if (path.endsWith("MainActivity.class")) {
                            ClassReader cr = null;
                            try {
                                cr = new ClassReader(it.bytes);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                            ClassVisitor classVisitor = new MyClassVisitor(cw);
                            cr.accept(classVisitor, ClassReader.SKIP_DEBUG);
                            byte[] data = cw.toByteArray();
                            new File(outDir, path).bytes = data
                        }
                        else {
                            new File(outDir, path).bytes = it.bytes
                        }
                    }
                }
            }
        }
    }
}
