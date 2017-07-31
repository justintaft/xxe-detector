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
---snip---
Jul 30, 2017 8:31:29 PM com.oneupsecurity.xxedetector.ExceptionLogger LogVuln
WARNING: INSECURE Document Builder created:
    com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl.newDocumentBuilder(DocumentBuilderFactoryImpl.java:75)
    at com.oneupsecurity.xxedetector.AnotherClass.insecureTest(Main.java:19)
    at com.oneupsecurity.xxedetector.Main.main(Main.java:39)
~~~


