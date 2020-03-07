#!/bin/sh
javac -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar ../../src/main/java/*.java
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar Utilities 9
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar PeerNode 5 Test_6_EDLAB.txt &
