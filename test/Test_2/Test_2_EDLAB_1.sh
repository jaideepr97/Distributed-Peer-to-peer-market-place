#!/bin/sh
javac -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar ../../src/main/java/*.java
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar Utilities 4
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar PeerNode 1 Test_2_EDLAB.txt &
