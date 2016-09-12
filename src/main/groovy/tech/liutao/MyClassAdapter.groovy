package tech.liutao

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 * Created by liutao on 9/12/16.
 */
public class MyClassVisitor extends ClassVisitor {

    public MyClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        MethodVisitor mv =  super.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wMv = mv;
        if(mv != null) {
            if(name.equals("onCreate")) {
                wMv = new MyMethodVisitor(mv);
            }
        }
        return wMv;
    }
}

class MyMethodVisitor extends MethodVisitor {

    public MyMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "tech/liutao/evaluateplugin/InsertCode", "insertedMethod", "()V");
    }
}
