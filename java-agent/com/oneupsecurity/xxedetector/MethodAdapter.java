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

        /* System.err.println("CALL" + name); */
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("CALL " + this.realName);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
  
        /* do call */
        mv.visitMethodInsn(opcode, owner, name, desc, itf);

        /* System.err.println("RETURN" + name);  */
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("RETURN " + name);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }
}
