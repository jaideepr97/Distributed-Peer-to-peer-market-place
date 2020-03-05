#!/bin/sh
javac -cp ../src/main/java:../src/main/java/gson-2.8.6.jar ../src/main/java/*.java
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar Utilities 12
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 1 Test12.txt &
java -cp ../src/main/java:../src/main/java/gson-2.8.6.jar PeerNode 2 Test12.txt &