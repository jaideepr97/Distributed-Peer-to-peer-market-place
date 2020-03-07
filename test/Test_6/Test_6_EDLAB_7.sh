#!/bin/sh
javac -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar ../../src/main/java/*.java
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar Utilities 9
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar PeerNode 4 Test_6_EDLAB.txt &
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar PeerNode 6 Test_6_EDLAB.txt &