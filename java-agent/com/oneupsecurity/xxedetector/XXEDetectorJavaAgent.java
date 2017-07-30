package com.oneupsecurity.xxedetector;
import java.lang.instrument.Instrumentation;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


class XXEDetectorJavaAgent implements ClassFileTransformer {
    public XXEDetectorJavaAgent() {
    }

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Instrumentor loaded");
        XXEDetectorJavaAgent xxeDetectorJavaAgent = new XXEDetectorJavaAgent();
        instrumentation.addTransformer(xxeDetectorJavaAgent);

    }


    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
      System.out.println("Class loaded: " + className);
      try {
        //Path path = Paths.get("classes/" + className + ".class");
        //Files.write(path, classfileBuffer); 
      } catch (Throwable ignored) { // ignored, don’t do this at home kids
      } finally { return classfileBuffer; }
    }
}
