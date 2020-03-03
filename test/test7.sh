#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 12
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 5 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 6 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 7 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 8 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 9 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 10 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 11 TwelveNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 12 TwelveNodes.txt &