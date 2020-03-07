#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 11
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 Test11.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 Test11.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 Test11.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 Test11.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 5 Test11.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 6 Test11.txt &