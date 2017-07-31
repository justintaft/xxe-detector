java-agent/dep/asm-all-5.2.jar:
	mkdir java-agent/dep
	curl http://central.maven.org/maven2/org/ow2/asm/asm-all/5.2/asm-all-5.2.jar > java-agent/dep/asm-all-5.2.jar

java-agent-tester-build: 
	find java-agent-tester -name "*.java" | xargs javac 
	cd java-agent-tester &&  find . -name "*.class" | xargs jar cmf manifest.txt java-agent-tester.jar

build-and-run: java-agent-tester-build
	find java-agent -name "*.java" | xargs javac -cp java-agent/dep/asm-all-5.2.jar
	cd java-agent && find . -name "*.class" | xargs jar cmf manifest.txt java-agent.jar 


	# Running main app without instrumentation
	java -jar java-agent-tester/java-agent-tester.jar

	# Running main app with instrumnetation
	java  -Xbootclasspath/p:java-agent/dep/asm-all-5.2.jar -javaagent:java-agent/java-agent.jar -jar java-agent-tester/java-agent-tester.jar


patchcode: java-agent/dep/asm-all-5.2.jar
	cd documentbuilder-patchcode && javac Test.java && java -cp ../java-agent/dep/asm-all-5.2.jar org.objectweb.asm.util.ASMifier Test.class

all: java-agent/dep/asm-all-5.2.jar build-and-run
