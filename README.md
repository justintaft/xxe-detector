# xxe-detector
Detects misconfigured XML parsers in Java through instrumentation.

Run `make all` to build and run example.


ASM patchode is based on documentbuilder-patchcode/Test.java. Run `make patchcode` to see patchcode of class.


# Details
Files under `java-agent` are used to create a class loader which performs instrumenation.

Files under `java-agent-tester` are for a project which creates a document builder. 
When java-agent-tester is ran without instrumentation, creation of document builders are not shown.
When java-sgent-tester is ran with instrumentation, creation of document builders is shown.


