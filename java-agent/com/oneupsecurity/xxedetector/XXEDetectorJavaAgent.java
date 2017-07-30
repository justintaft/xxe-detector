package com.oneupsecurity.xxedetector;


import com.oneupsecurity.xxedetector.ClassAdapter;

import java.lang.instrument.Instrumentation;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import org.objectweb.asm.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;


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

        byte[] returnValue = classfileBuffer;
       
        //TODO this will most likely change based on what version of java is being used...
        //String targetClassName = "com/sun/org/apache/xerces/internal/jaxp/DocumentBuilderFactoryImpl"; 
        //String targetClassName = "javax/xml/parsers/DocumentBuilderFactory";
        String targetClassName = "com/sun/org/apache/xerces/internal/jaxp/DocumentBuilderFactoryImpl"; 
        //String targetClassName = "com/oneupsecurity/xxedetector/Main"; 

        if(className.equals(targetClassName)) {
           System.out.println("Class loaded: " + className);

            try { 
            FileOutputStream fos = new FileOutputStream("/tmp/builderimpl.class");
            fos.write(classfileBuffer);
            fos.close();
            } catch (Exception e) {}

            System.out.println("Instrumenting: " + targetClassName );

            try {
                InputStream classInputByteStream = new ByteArrayInputStream(classfileBuffer); 

                ClassReader classReader=new ClassReader(classInputByteStream);
                ClassWriter cw=new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);

                classReader.accept(new ClassAdapter(cw), 0);
                returnValue = cw.toByteArray();

            } 
            catch (Throwable e) { System.out.println("Something happened: " + e); e.printStackTrace(System.out);} 
        }

        //Path path = Paths.get("classes/" + className + ".class");
        //Files.write(path, classfileBuffer); 
        return returnValue;
    }
}

