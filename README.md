# xxe-detector
Detects insecurly configured XML parsers in Java through instrumentation.

Run `make all` to build and run example.

ASM patchode is based on documentbuilder-patchcode/Test.java. Run `make patchcode` to see patchcode of class.


# Details
Files under `java-agent` are used to create a class loader which performs instrumenation.

Files under `java-agent-tester` are for a project which creates a document builder. 
When java-agent-tester is ran without instrumentation, creation of document builders are not shown.
When java-sgent-tester is ran with instrumentation, creation of document builders is shown.


# Example Of Instrumented Program

Note log messages are bit messy when instrumentation is occuring. Once done, only "WARN" messages will appear.

~~~
java  -Xbootclasspath/p:java-agent/dep/asm-all-5.2.jar:java-agent/java-agent.jar -javaagent:java-agent/java-agent.jar -jar java-agent-tester/java-agent-tester.jar
objc[90154]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/bin/java (0x10922c4c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x1093264e0). One of the two will be used. Which one is undefined.
Jul 30, 2017 8:15:16 PM com.oneupsecurity.xxedetector.XXEDetectorJavaAgent premain
INFO: Instrumentor loaded
Creating secure document builder...
Jul 30, 2017 8:15:16 PM com.oneupsecurity.xxedetector.XXEDetectorJavaAgent transform
INFO: Instrumentor com/sun/org/apache/xerces/internal/jaxp/DocumentBuilderFactoryImpl
Creating insecure document builder...
Jul 30, 2017 8:15:16 PM com.oneupsecurity.xxedetector.ExceptionLogger LogVuln
WARNING: INSECURE Document Builder created:
    com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl.newDocumentBuilder(DocumentBuilderFactoryImpl.java:75)
    at com.oneupsecurity.xxedetector.AnotherClass.insecureTest(Main.java:15)
    at com.oneupsecurity.xxedetector.Main.main(Main.java:33) 
~~~


