package com.oneupsecurity.xxedetector;


import com.oneupsecurity.xxedetector.ClassAdapter;
import com.oneupsecurity.xxedetector.ExceptionLogger;

import java.lang.instrument.Instrumentation;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import org.objectweb.asm.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.logging.Logger;
import java.util.logging.Level;



class XXEDetectorJavaAgent implements ClassFileTransformer {

    public static final Logger LOGGER = Logger.getLogger( XXEDetectorJavaAgent.class.getName() );

    public XXEDetectorJavaAgent() {
    }
    

    public static void premain(String args, Instrumentation instrumentation) {
        LOGGER.log(Level.INFO,"Instrumentor loaded");
        XXEDetectorJavaAgent xxeDetectorJavaAgent = new XXEDetectorJavaAgent();
        instrumentation.addTransformer(xxeDetectorJavaAgent);
        ExceptionLogger.doNothing();
    }


    @Override
    public byte[] transform(ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] returnValue = classfileBuffer;
       
        //TODO this will most likely change based on what version of java is being used...
        String targetClassName = "com/sun/org/apache/xerces/internal/jaxp/DocumentBuilderFactoryImpl"; 
        //String targetClassName = "javax/xml/parsers/DocumentBuilderFactory";

        if(className.equals(targetClassName)) {
            LOGGER.log(Level.INFO,"Instrumentor " + targetClassName);
            try {
                InputStream classInputByteStream = new ByteArrayInputStream(classfileBuffer); 

                ClassReader classReader=new ClassReader(classInputByteStream);
                ClassWriter cw=new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);

                classReader.accept(new ClassAdapter(cw), 0);
                returnValue = cw.toByteArray();

            } 
            catch (Throwable e) { System.out.println("Something happened: " + e); e.printStackTrace(System.out);} 
        }

        return returnValue;
    }
}

