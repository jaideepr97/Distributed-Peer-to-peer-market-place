#!/bin/sh
javac -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar ../../src/main/java/*.java
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar Utilities 6
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar PeerNode 1 Test_3_EDLAB.txt &
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar PeerNode 2 Test_3_EDLAB.txt &