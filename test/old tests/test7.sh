#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 7
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 5 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 6 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 7 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 8 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 9 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 10 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 11 Test7.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 12 Test7.txt &