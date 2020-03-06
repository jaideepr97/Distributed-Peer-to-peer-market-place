#!/bin/sh
javac -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar ../../src/main/java/*.java
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar Utilities 1
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar PeerNode 3 Test_1_local.txt &
java -cp ../../src/main/java:../../src/main/java/gson-2.8.6.jar PeerNode 4 Test_1_local.txt &
