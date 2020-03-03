#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 8
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 EightNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 EightNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 EightNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 EightNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 5 EightNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 6 EightNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 7 EightNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 8 EightNodes.txt &