package com.oneupsecurity.xxedetector;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.StringWriter;
import java.io.PrintWriter;


public class ExceptionLogger {

    public static final Logger LOGGER = Logger.getLogger( XXEDetectorJavaAgent.class.getName() );

    private static StringWriter sw = new StringWriter();
    private static PrintWriter pw = new PrintWriter(sw);

    //Used to force-load the class
    public static void doNothing() {}

    public static void LogVuln() {

        new Exception().printStackTrace(pw);
        String stacktrace = sw.toString();
        
        //Remove our functio calls in the stacktrace 
        stacktrace = stacktrace.substring(107);
        LOGGER.log(Level.WARNING, "INSECURE Document Builder created:\n\t"  + stacktrace);
    }

}
