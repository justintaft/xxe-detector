all:
	find java-agent -name "*.java" | xargs javac 
	cd java-agent && find . -name "*.class" | xargs jar cmf manifest.txt java-agent.jar 

	find java-agent-tester -name "*.java" | xargs javac 
	cd java-agent-tester &&  find . -name "*.class" | xargs jar cmf manifest.txt java-agent-tester.jar

	# Running main app without instrumentation
	java -jar java-agent-tester/java-agent-tester.jar

	# Running main app with instrumnetation
	java -javaagent:java-agent/java-agent.jar -jar java-agent-tester/java-agent-tester.jar
