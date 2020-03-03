#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 17
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 5 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 6 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 7 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 8 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 9 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 10 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 11 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 12 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 13 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 14 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 15 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 16 SeventeenNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 17 SeventeenNodes.txt &