# xxe-detector
Attempts to detect insecurly configured XML parsers in Java through instrumentation.
Currently just checks for feature "http://apache.org/xml/features/disallow-doctype-decl" not set for DocumentBuilderFactorys.

Run `make all` to build and run example.

# Example Of Instrumented Program
~~~
java  -Xbootclasspath/p:java-agent/dep/asm-all-5.2.jar:java-agent/java-agent.jar -javaagent:java-agent/java-agent.jar -jar java-agent-tester/java-agent-tester.jar
---snip---
WARNING: Documentbuilder does not have disallow-doctype-decl set:
    com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl.newDocumentBuilder(DocumentBuilderFactoryImpl.java:75)
    at com.oneupsecurity.xxedetector.AnotherClass.insecureTest(Main.java:19)
    at com.oneupsecurity.xxedetector.Main.main(Main.java:39)
~~~


# Details
Files under `java-agent` are used to create a class loader which performs instrumenation.

Files under `java-agent-tester` are for a project which creates a document builder. 
When java-agent-tester is ran without instrumentation, creation of document builders are not shown.
When java-sgent-tester is ran with instrumentation, creation of document builders is shown.

ASM patchode is based on documentbuilder-patchcode/Test.java. Run `make patchcode` to see patchcode of class.


