#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 4
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 Test4.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 Test4.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 Test4.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 Test4.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 5 Test4.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 6 Test4.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 7 Test4.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 8 Test4.txt &