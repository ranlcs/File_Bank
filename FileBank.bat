@echo off
set a="d:/Projet/java/SourceMesClasses/FileBank"
cd %a%
cd Partie
javac *.java -d %A%/bin -cp %A%/bin;%A%/bin/jar/opencv-450.jar;%A%/bin/jar/mysql-connector.jar
cd..
javac *.java -d %A%/bin -cp %A%/bin;%A%/bin/jar/opencv-450.jar;%A%/bin/jar/mysql-connector.jar
cd bin
java -cp %A%/bin;%A%/bin/opencv-450.jar;%A%/bin/jar/mysql-connector.jar Frame 
cd..
@echo on