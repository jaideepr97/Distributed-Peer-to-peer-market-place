#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 9
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 NineNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 NineNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 NineNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 NineNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 5 NineNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 6 NineNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 7 NineNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 8 NineNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 9 NineNodes.txt &