#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 6
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 SixNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 SixNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 3 SixNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 4 SixNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 5 SixNodes.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 6 SixNodes.txt &