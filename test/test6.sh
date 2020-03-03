#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 7
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 SevenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 SevenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 SevenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 SevenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 5 SevenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 6 SevenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 7 SevenNodes.txt &