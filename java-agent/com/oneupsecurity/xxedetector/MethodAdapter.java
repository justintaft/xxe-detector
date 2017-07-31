package com.oneupsecurity.xxedetector;

import org.objectweb.asm.*;

class MethodAdapter extends MethodVisitor implements Opcodes {

    public MethodAdapter(final MethodVisitor mv) {
        super(ASM5, mv);
    }


    public String realName;

    public MethodAdapter setRealName(String name) {
        this.realName = name;
        return this;
    }

    @Override

    /**
    * @param name name of instruction?
    **/
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {

        //System.out.println("Instrumenting " + name + " " + owner +" " + desc);
        //if(!name.equals("newDocumnetBuilder")) {
        //    return;
        //}

        

        ///* System.err.println("CALL" + name); */
        //mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        //mv.visitLdcInsn("CALL " + this.realName);
        //mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
  
        /* do call */
        mv.visitMethodInsn(opcode, owner, name, desc, itf);

        //If newDocumentBuilder is called, check if doctype-decl is set
        if(this.realName.equals("newDocumentBuilder")) {
            mv.visitVarInsn(ALOAD, 0);
            mv.visitLdcInsn("http://apache.org/xml/features/disallow-doctype-decl");
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/sun/org/apache/xerces/internal/jaxp/DocumentBuilderFactoryImpl", "getFeature", "(Ljava/lang/String;)Z", false);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNE, l0);

            mv.visitMethodInsn(INVOKESTATIC, "com/oneupsecurity/xxedetector/ExceptionLogger", "LogVuln", "()V", false);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }
    }
}
