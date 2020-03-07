#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 2
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 Test2.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 Test2.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 Test2.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 Test2.txt &
